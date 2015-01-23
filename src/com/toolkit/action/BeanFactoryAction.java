/**
 * 
 */
package com.toolkit.action;

/**
 * @author OprCalf
 * 
 *         2014-12-26 AM 8:44:33
 */
public class BeanFactoryAction {

	private static BeanFactoryAction af;

	private BeanFactoryAction() {
	}

	public static BeanFactoryAction getActionFactory() {
		if (af == null) {
			af = new BeanFactoryAction();
		}
		return af;
	}

	public Object getAction(String ActionClassName) {
		Class<?> action = null;
		try {
			action = Class.forName(ActionClassName);
			return (action.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
