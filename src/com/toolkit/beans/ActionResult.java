/**
 * 
 */
package com.toolkit.beans;

import java.util.List;

/**
 * @author OprCalf
 * 
 *         December 30, 2014 9:13:50 AM
 * @param <T>
 */
public class ActionResult {

	private String urlName;
	private String jsonStr;
	private String filePath;
	private String fileName;
	private int returnType;
	private String modelName;
	private Object o;
	private List<Object> os;

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getReturnType() {
		return returnType;
	}

	public void setReturnType(int returnType) {
		this.returnType = returnType;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public List<Object> getOs() {
		return os;
	}

	public void setOs(List<Object> os) {
		this.os = os;
	}

}
