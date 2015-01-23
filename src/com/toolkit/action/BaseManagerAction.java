/**
 * 
 */
package com.toolkit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.toolkit.beans.ActionResult;
import com.toolkit.enums.ActionResultType;

/**
 * @author OprCalf
 * 
 *         2014-12-26 AM 8:35:54
 * @param <T>
 */
public class BaseManagerAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		try {
			String pathName = request.getServletPath();
			int index = pathName.indexOf(".");
			String baseActionName = pathName.substring(1, index);

			index = baseActionName.indexOf("!");
			String actionName = baseActionName.substring(0, index);

			index = baseActionName.indexOf("=");
			String methodName = baseActionName.substring(index + 1, baseActionName.length());

			String ActionPakName = this.getInitParameter("ActionPakName");

			Object action = BeanFactoryAction.getActionFactory().getAction(ActionPakName + actionName);
			ActionResult arb = this.runMethodBean(action, methodName, request, response);

			if (arb != null) {
				switch (arb.getReturnType()) {
				case ActionResultType.view:
					request.getRequestDispatcher(arb.getUrlName()).forward(request, response);
					break;
				case ActionResultType.viewModel:
					request.setAttribute(arb.getModelName(), arb.getO());
					request.getRequestDispatcher(arb.getUrlName()).forward(request, response);
					break;
				case ActionResultType.viewModels:
					request.setAttribute(arb.getModelName(), arb.getOs());
					request.getRequestDispatcher(arb.getUrlName()).forward(request, response);
					break;
				case ActionResultType.json:
					response.setContentType("text/json; charset=UTF-8");
					response.getOutputStream().write(arb.getJsonStr().toString().getBytes("UTF-8"));
					break;
				case ActionResultType.downloadFile:
					String filepath = arb.getFilePath();
					String downFilename = arb.getFileName();
					File dfile = new File(filepath + downFilename);
					FileInputStream in = new FileInputStream(dfile);
					OutputStream out = response.getOutputStream();
					response.setContentType("Application/Octet-stream;charset=utf-8");
					response.addHeader("Content-Disposition",
							"attachment; filename=" + new String(downFilename.getBytes("GBK"), "ISO8859_1") + "\"");
					response.setContentLength((int) dfile.length());
					byte[] bs = new byte[1024];
					while (in.read(bs) > 0) {
						out.write(bs);
					}
					out.close();
					in.close();
					break;
				default:
					request.getRequestDispatcher("error.jsp").forward(request, response);
					break;
				}

			} else {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private ActionResult runMethodBean(Object t, String metName, HttpServletRequest request,
			HttpServletResponse response) {
		ActionResult ars = null;
		try {
			Class<?> clazz = t.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				Object arg[] = new Object[2];
				arg[0] = request;
				arg[1] = response;
				if (methodName.equals(metName)) {
					ars = (ActionResult) method.invoke(t, arg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ars;
	}

	public HttpSession getSession(HttpServletRequest request) {
		return request.getSession(false) == null ? request.getSession() : request.getSession(false);
	}
}