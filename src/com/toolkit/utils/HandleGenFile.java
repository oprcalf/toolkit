/**
 * 
 */
package com.toolkit.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.toolkit.utils.inf.HandleGenFileInf;

/**
 * @author OprCalf
 * 
 *         2014-12-26 AM 10:14:17
 * 
 *         此类用于生成逗号分隔符文件
 */
public class HandleGenFile implements HandleGenFileInf {

	private String filePath = "";
	private String fileSpilt = ",";
	private String fileType = ".csv";

	public HandleGenFile() {

	}

	public HandleGenFile(String filePath, String fileSpilt, String fileType) {
		this.filePath = filePath;
		this.fileSpilt = fileSpilt;
		this.fileType = fileType;
	}

	@Override
	public void rsToFile(ResultSet rs) {
		List<StringBuilder> listStrbuilder = null;
		listStrbuilder = this.rsToStrBur(rs);
		this.genFileWithBuilder(listStrbuilder);
	}

	@Override
	public void rsToFile(ResultSet rs, String[] str) {
		List<StringBuilder> listStrbuilder = null;
		listStrbuilder = this.rsToStrBur(rs, str);
		this.genFileWithBuilder(listStrbuilder);
	}

	@Override
	public <T> void beansToFile(List<T> ts) {
		List<StringBuilder> listStrbuilder = null;
		listStrbuilder = this.beansToStrBur(ts);
		this.genFileWithBuilder(listStrbuilder);
	}

	private void genFileWithBuilder(List<StringBuilder> listStrbuilder) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			String strFilePath = this.filePath + this.fileType;
			this.createFile(strFilePath);
			csvFile = new File(strFilePath);
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "utf8"),
					1024);
			if (listStrbuilder != null && listStrbuilder.size() > 0) {
				for (StringBuilder sb : listStrbuilder) {
					csvFileOutputStream.write(sb.toString());
					csvFileOutputStream.newLine();
				}
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private List<StringBuilder> rsToStrBur(ResultSet rs) {
		List<StringBuilder> strBuilderArr = new ArrayList<StringBuilder>();
		StringBuilder sb = null;
		String colName = "";
		String colValue = "";
		String spiltType = this.fileSpilt;

		if (rs != null) {
			try {
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				while (rs.next()) {
					// Calculate start from 1 in JDBC
					sb = new StringBuilder();
					for (int i = 1; i <= count; i++) {
						colName = rsmd.getColumnName(i);
						colValue = rs.getString(colName) == null ? "" : rs.getString(colName);
						sb.append(colValue);
						sb.append(i == count ? "" : spiltType);
					}
					strBuilderArr.add(sb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return strBuilderArr;

	}

	private List<StringBuilder> rsToStrBur(ResultSet rs, String[] str) {
		List<StringBuilder> listStrBuil = new ArrayList<StringBuilder>();
		StringBuilder sb = null;
		String colName = "";
		String colValue = "";
		String spiltType = this.fileSpilt;

		if (rs != null && str != null && str.length > 0) {
			try {
				int count = str.length;
				while (rs.next()) {
					sb = new StringBuilder();
					for (int i = 0; i < count; i++) {
						colName = str[i];
						colValue = rs.getString(colName) == null ? "" : rs.getString(colName);
						sb.append(colValue);
						sb.append((i == count - 1) ? "" : spiltType);
					}
					listStrBuil.add(sb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listStrBuil;

	}

	private <T> List<StringBuilder> beansToStrBur(List<T> ts) {
		List<StringBuilder> strBuilderArr = new ArrayList<StringBuilder>();
		HandleReflectBean hrb = new HandleReflectBean();
		StringBuilder sb = null;
		String spiltType = this.fileSpilt;
		if (ts != null && ts.size() > 0) {
			for (T t : ts) {
				try {
					sb = hrb.ObjectParesToString(t, spiltType);
					if (sb != null)
						strBuilderArr.add(sb);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return strBuilderArr;
	}

	private void createFile(String filePath) {
		File file = new File(filePath);
		try {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				file.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
