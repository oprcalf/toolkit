/**
 * 
 */
package com.toolkit.action;

import java.util.List;

import com.toolkit.beans.ActionResult;
import com.toolkit.enums.ActionResultType;

/**
 * @author OprCalf
 * 
 *         December 30, 2014 2:24:19 PM
 * @param <T>
 */
public class Action {

	private ActionResult actionResult;

	public ActionResult View(String urlName) {
		actionResult = new ActionResult();
		actionResult.setUrlName(urlName);
		actionResult.setReturnType(ActionResultType.view);
		return actionResult;
	}

	public ActionResult View(String urlName, Object o) {
		actionResult = new ActionResult();
		actionResult.setO(o);
		actionResult.setUrlName(urlName);
		actionResult.setModelName("model");
		actionResult.setReturnType(ActionResultType.viewModel);
		return actionResult;
	}

	public ActionResult View(String urlName, Object o, String modelName) {
		actionResult = new ActionResult();
		actionResult.setO(o);
		actionResult.setUrlName(urlName);
		actionResult.setModelName(modelName);
		actionResult.setReturnType(ActionResultType.viewModel);
		return actionResult;
	}

	public ActionResult View(String urlName, List<Object> os) {
		actionResult = new ActionResult();
		actionResult.setUrlName(urlName);
		actionResult.setOs(os);
		actionResult.setReturnType(ActionResultType.viewModels);
		actionResult.setModelName("model");
		return actionResult;
	}

	public ActionResult View(String urlName, List<Object> os, String modelName) {
		actionResult = new ActionResult();
		actionResult.setUrlName(urlName);
		actionResult.setOs(os);
		actionResult.setReturnType(ActionResultType.viewModels);
		actionResult.setModelName(modelName);
		return actionResult;
	}

	public ActionResult JsonView(String jsonStr) {
		actionResult = new ActionResult();
		actionResult.setJsonStr(jsonStr);
		actionResult.setReturnType(ActionResultType.json);
		return actionResult;
	}

	public ActionResult DownloadFile(String filePath, String fileName) {
		actionResult = new ActionResult();
		actionResult.setFilePath(filePath);
		actionResult.setFileName(fileName);
		actionResult.setReturnType(ActionResultType.downloadFile);
		return actionResult;
	}

}
