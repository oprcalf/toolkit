/**
 * 
 */
package com.toolkit.utils.inf;

import java.sql.ResultSet;

/**
 * @author OprCalf
 * 
 *         December 26, 2014 5:44:48 PM
 */
public interface HandleRsToBeanInf {

	<T> T getObjectBean(T t, ResultSet rs);

	<T> T getObjectBeanWithAnno(T t, ResultSet rs);

}
