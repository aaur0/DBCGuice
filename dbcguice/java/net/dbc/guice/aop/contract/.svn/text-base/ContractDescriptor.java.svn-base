package net.dbc.guice.aop.contract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.dbc.guice.Alias;
import net.dbc.guice.util.ReflectionUtil;

import org.aopalliance.intercept.MethodInvocation;

/**
 * Describes a contract. It encapsulates the condition to evaluate, the
 * evaluation context (typically a mapping with the arguments of the method and
 * the aliases) and the human-readable description for the contract, as well as
 * other related information.
 * 
 * @author raul.bajales@gmail.com
 */
public class ContractDescriptor {

	private String eval;
	private String desc;
	private String methodSignature;
	private Class<?> clazz;
	private ContractType contractType;
	private boolean valid = true;

	private Map<String, Object> context;

	protected ContractDescriptor() {
	}

	/**
	 * Builds a list of instances of this class, taking information from the
	 * method and the annotation class. Basically it takes all the contracts
	 * defined in all the hierarchy (including implemented interfaces) for the
	 * invoked method.
	 * 
	 * @param invocation
	 *            The method invocation.
	 * @param type
	 *            The contract type
	 * @see ContractType
	 * @return A new contract descriptor.
	 */
	public static final List<ContractDescriptor> buildListFor(
			MethodInvocation invocation, ContractType type) {		
		List<ContractDescriptor> retValue = new ArrayList<ContractDescriptor>();
		Method method = invocation.getMethod();
		Class<?> startingClass = method.getDeclaringClass();
		for (Class<?> clazz : ReflectionUtil
				.getClassesAndInterfaceInHierarchy(startingClass)) {
			Method thisMethod = null;
			try {
				thisMethod = clazz.getMethod(method.getName(), method
						.getParameterTypes());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			for (Annotation annotation :  thisMethod.getAnnotations()) {
				if (type.getType().isAssignableFrom(annotation.getClass())) {
					ContractDescriptor descriptor = buildDescriptor(invocation,
							type, clazz, thisMethod, annotation, true);
					retValue.add(descriptor);
				}
			}
			Annotation invariant = clazz.getAnnotation(ContractType.INVARIANT.getType());
			if (invariant != null) {
				ContractDescriptor descriptor = buildDescriptor(invocation, type, clazz, 
						thisMethod, invariant, false);
				retValue.add(descriptor);
			}
		}
		return retValue;
	}

	private static ContractDescriptor buildDescriptor(
			MethodInvocation invocation, ContractType type, Class<?> clazz,
			Method thisMethod, Annotation annotation, boolean isMethodAnnotation) {
		ContractDescriptor descriptor = new ContractDescriptor();
		descriptor.contractType = type;
		descriptor.context = collectAliases(invocation, thisMethod, isMethodAnnotation);
		descriptor.methodSignature = buildMethodSignature(thisMethod,
				descriptor.context);
		descriptor.clazz = clazz;
		descriptor.desc = invokeMethod("desc", annotation);
		descriptor.eval = invokeMethod("value", annotation);
		if (descriptor.eval.equals(""))
			descriptor.valid = false;
		return descriptor;
	}

	private static String buildMethodSignature(Method method,
			Map<String, Object> context) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		String retValue = method.getReturnType().getCanonicalName() + " "
				+ method.getName() + "(";
		Iterator<String> contextIter = context.keySet().iterator();
		for (Class<?> clazz : parameterTypes)
			retValue += clazz.getCanonicalName() + " " + contextIter.next()
					+ ", ";
		return retValue.substring(0, retValue.length() - 2) + ")";
	}

	/**
	 * Inherited annotations are not supported in Java, so for now invoke getter
	 * methods by reflection to get the property values in the annotations.
	 * 
	 * @param methodName
	 *            The method to be executed (with no arguments), as for all the
	 *            annotations, is expected to return a String.
	 * @param annotationInstance
	 *            The annotation instance.
	 * @return The value of the property from the annotation.
	 */
	private static String invokeMethod(String methodName,
			Object annotationInstance) {
		String retValue = "";
		try {
			Method method = annotationInstance.getClass().getMethod(methodName,
					new Class[] {});
			retValue = (String) method.invoke(annotationInstance,
					new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retValue;
	}

	/**
	 * Collect all the aliases from the method signature. If the Alias
	 * annotation is used then the defined name is taken for that argument,
	 * otherwise, a name is created using the ContextConstants.DEFAULT_ALIAS_PREFIX 
	 * plus the index number for that argument (starting from 0 for the first
	 * argument).
	 * 
	 * @param invocation
	 *            MethodInvocation
	 * @param isMehodAnnotation 
	 * @return A map containing all the arguments with an alias as a key.
	 * @throws RuntimeException
	 *             if an alias is defined with an empty name.
	 */
	private static final Map<String, Object> collectAliases(MethodInvocation invocation,
			Method method, boolean isMethodAnnotation) {
		Map<String, Object> retValue = new LinkedHashMap<String, Object>();
		if (isMethodAnnotation) {
			Object[] arguments = invocation.getArguments();
			for (int i = 0; i < arguments.length; i++) {
				retValue.put(ContextConstants.DEFAULT_ALIAS_PREFIX + i, arguments[i]);
			}
			Annotation[][] argAnnotations = method
					.getParameterAnnotations();
			for (int i = 0; i < argAnnotations.length; i++) {
				for (int j = 0; j < argAnnotations[i].length; j++) {
					String key = "";
					if (argAnnotations[i][j] instanceof Alias) {
						key = ((Alias) argAnnotations[i][j]).value();
						if (key == null || key.equals(""))
							throw new RuntimeException(
									"Alias name cannot be null or empty!, method:"
											+ method.getName());
					}
					if (!key.equals("")) {
						retValue.remove(ContextConstants.DEFAULT_ALIAS_PREFIX + i);
						retValue.put(key, arguments[i]);
					}
				}
			}
		}
		retValue.put(ContextConstants.INSTANCE_REFERENCE_ALIAS, invocation.getThis());
		return retValue;
	}

	public String getEval() {
		return eval;
	}

	public String getDesc() {
		return desc;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public boolean isValid() {
		return valid;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public Map<String, Object> getContext() {
		return context;
	}

	@Override
	public String toString() {
		String retValue = contractType.getName() + ": " + eval + "\n";
		retValue += (desc != null && !desc.equals("")) ? "Description: " + desc
				+ "\n" : "";
		retValue += "Method: " + methodSignature + "\n";
		retValue += "Class: " + clazz + "\n";
		retValue += (context != null && !context.isEmpty()) ? "Context:\n"
				+ describeMap(context) : "";
		return retValue;
	}

	private String describeMap(Map<String, Object> map) {
		String retValue = "";
		for (String key : map.keySet())
			retValue += key + " = " + map.get(key) + "\n";
		return retValue;
	}
}
