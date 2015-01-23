/**
 * 
 */
package com.toolkit.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author OprCalf
 * 
 *         December 10, 2014
 * 
 * 
 *         此类用于把JavaBean对象转成StringBulider对象
 */
public class HandleReflectBean {

	protected StringBuilder ObjectParesToString(Object obj, String spiltType) throws Exception {
		if (obj != null) {
			Class<?> selfClass = Class.forName(obj.toString().split("@")[0]);// 加载实例
			Field[] fields = selfClass.getDeclaredFields();// 获取所有属性
			String fdname = null;
			Method metd = null;
			int count = 0;
			boolean flag = false;
			StringBuilder sb = new StringBuilder();
			for (Field field : fields) {// 遍历该数组
				fdname = field.getName();// 获取字段名
				metd = selfClass.getMethod("get" + change(fdname));// 根据字段名找到对应的get方法，null表示无参数
				Object name = null;
				name = metd.invoke(obj);// 调用该字段的get方法
				if (name == null) {
					name = "";
				}
				count++;
				flag = (count == fields.length ? true : false);
				sb.append(name + (flag ? "" : spiltType));
			}
			return sb;
		} else {
			return null;
		}
	}

	/**
	 * @param src
	 *            源字符串
	 * @return 字符串，将String的第一个字母转换为大写，String为空时返回null
	 */
	private String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}
}
