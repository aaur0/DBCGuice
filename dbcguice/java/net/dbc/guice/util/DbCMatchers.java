package net.dbc.guice.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.dbc.guice.HasContract;
import net.dbc.guice.aop.contract.ContractType;
import net.dbc.guice.module.AbstractDbCGuiceModule;

import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matcher;

/**
 * Some matchers used to define the interception rules in the provided Guice module.
 * @see AbstractDbCGuiceModule
 * 
 * @author raul.bajales@gmail.com
 */
public class DbCMatchers {

	private static List<Class<? extends Annotation>> methodAnnotations = new ArrayList<Class<? extends Annotation>>();

	private DbCMatchers() {
		super();
	}

	/**
	 * This matcher matches any method with a contract definition (it means, with any contract
	 * definition annotation), if the current method does not have any contract, then it looks for
	 * the same method but in the superclass, and goes on until reach the Object class, also checks
	 * implemented interfaces. It means that a contract can be defined anywhere in the hierarchy.
	 * Also it matches a method without any contract definition only if any class/interface in the 
	 * hierarchy has an Invariant annotation.
	 * 
	 * @return The matcher.
	 */
	public static Matcher<Method> hasContractInMethodHierarchy() {
		return new AbstractMatcher<Method>() {
			public boolean matches(Method method) {
				Class<?> startingClass = method.getDeclaringClass();
				for (Class<?> clazz : ReflectionUtil
						.getClassesAndInterfaceInHierarchy(startingClass)) {
					Method thisMethod = null;
					try {
						thisMethod = clazz.getMethod(method.getName(), method
								.getParameterTypes());
					} catch (SecurityException e) {
					} catch (NoSuchMethodException e) {
					}
					for (Class<? extends Annotation> contractAnnotation : methodAnnotations)
						if (thisMethod.getAnnotation(contractAnnotation) != null)
							return true;
					if (clazz.getAnnotation(ContractType.INVARIANT.getType()) != null)
						return true;
				}
				return false;
			}

			public String toString() {
				return "hasContractInMethodHierarchy()";
			}
		};
	}

	/**
	 * This matcher matches any class with a contract definition (it means, with the HasContract
	 *  annotation), if the current class does not have it, then it looks for it in the superclass, 
	 *  and goes on until reach the Object class, also check implemented interfaces. 
	 *  It means that the HasContract annotation can be used anywhere in the hierarchy.
	 * 
	 * @return The matcher.
	 */
	public static Matcher<Class<?>> hasContractInClassHierarchy() {
		return new AbstractMatcher<Class<?>>() {
			public boolean matches(Class<?> startingClass) {
				for (Class<?> clazz : ReflectionUtil
						.getClassesAndInterfaceInHierarchy(startingClass))
					if (clazz.getAnnotation(HasContract.class) != null)
						return true;
				return false;
			}

			public String toString() {
				return "hasContractInClassHierarchy()";
			}
		};
	}

	static {
		methodAnnotations.add(ContractType.PRECONDITION.getType());
		methodAnnotations.add(ContractType.POSTCONDITION.getType());
		methodAnnotations.add(ContractType.NEVERRETURNSNULL.getType());
	}
}
