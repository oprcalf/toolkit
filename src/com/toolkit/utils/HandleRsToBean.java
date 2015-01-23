/**
 * 
 */
package com.toolkit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.toolkit.utils.inf.HandleRsToBeanInf;
import com.toolkit.annotation.DBAnnotation;

/**
 * @author OprCalf
 * 
 *         December 26, 2014 5:44:03 PM
 *         
 *         此类用于把ResultSet对象转成需要的JavaBean对象
 */
public class HandleRsToBean implements HandleRsToBeanInf {

	public <T> T getObjectBean(T t, ResultSet rs) {
		try {
			if (t != null && rs != null) {
				Class<?> c = null;
				ResultSetMetaData rsmd = null;
				int count = 0;
				String colName = "";
				String colValue = "";
				c = t.getClass();
				rsmd = rs.getMetaData();
				count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {

					colName = rsmd.getColumnLabel(i);
					PropertyDescriptor pd = new PropertyDescriptor(colName, c);
					colValue = rs.getString(colName) == null ? "" : rs.getString(colName);
					Method method = pd.getWriteMethod();
					method.setAccessible(true);
					method.invoke(t, colValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public <T> T getObjectBeanWithAnno(T t, ResultSet rs) {
		try {
			if (t != null && rs != null) {
				Class<?> c = null;
				ResultSetMetaData rsmd = null;
				int count = 0;
				String colName = "";
				String colValue = "";
				c = t.getClass();
				Field[] fields = c.getDeclaredFields();
				rsmd = rs.getMetaData();
				count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					colName = rsmd.getColumnName(i);
					for (Field field : fields) {
						DBAnnotation meta = field.getAnnotation(DBAnnotation.class);
						if (meta != null && meta.colName().equals(colName)) {
							PropertyDescriptor pd = new PropertyDescriptor(field.getName(), c);
							colValue = rs.getString(colName) == null ? "" : rs.getString(colName);
							Method method = pd.getWriteMethod();
							method.setAccessible(true);
							method.invoke(t, colValue);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
