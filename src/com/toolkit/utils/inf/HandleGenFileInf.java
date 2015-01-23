/**
 * 
 */
package com.toolkit.utils.inf;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author OprCalf
 * 
 *         2014-12-26 AM 9:46:17
 */
public interface HandleGenFileInf {

	void rsToFile(ResultSet rs);

	void rsToFile(ResultSet rs, String[] str);

	<T> void beansToFile(List<T> ts);

}
