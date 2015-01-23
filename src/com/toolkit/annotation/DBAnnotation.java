/**
 * 
 */
package com.toolkit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author OprCalf
 * 
 *         December 26, 2014 5:48:04 PM
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
		ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.PACKAGE })
public @interface DBAnnotation {

	String dbName() default "";

	String tabName() default "";

	String colName() default "";

	String colType() default "";

}
