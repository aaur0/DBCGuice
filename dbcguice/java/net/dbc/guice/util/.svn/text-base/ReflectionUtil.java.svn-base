package net.dbc.guice.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class used by the provided Guice matchers.
 * 
 * @see DbCMatchers
 * 
 * @author raul.bajales@gmail.com
 * @author JohnnyK3@gmail.com
 */
public class ReflectionUtil {

	/**
	 * Looks for classes and interfaces starting from a provided class, and
	 * going up in the hierarchy until reach the Object class.
	 * 
	 * @param c
	 *            The starting class in the search.
	 * @return The set of classes and interfaces.
	 */
	public static Set<Class<?>> getClassesAndInterfaceInHierarchy(Class<?> c) {
		Set<Class<?>> result = new HashSet<Class<?>>();
		if (c != null && c != Object.class) {
			result.add(c);
			for (Class<?> i : c.getInterfaces()) {
				result.addAll(getClassesAndInterfaceInHierarchy(i));
			}
			result.addAll(getClassesAndInterfaceInHierarchy(c.getSuperclass()));
		}
		return result;
	}
}
