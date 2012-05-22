package net.dbc.guice.tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import junit.framework.TestCase;
import net.dbc.guice.util.ReflectionUtil;

public class ReflectionUtilsTestCase extends TestCase {

	/**
	 * Ensure that ReflectionUtil.getClassesAndInterfaceInHierarchy gets all
	 * classes in a hierarchy including all interfaces and interface parents.
	 */
	public void testGetClassesAndInterfaceInHierarchy() {
		Collection<Class<?>> cls = ReflectionUtil
				.getClassesAndInterfaceInHierarchy(MyImplClass.class);
		@SuppressWarnings("unchecked")
		List<Class<? extends MyIntParent>> expected = Arrays.asList(
				MyImplClass.class, MyInt1.class, MyParentClass.class,
				MyInt2.class, MyIntParent.class);
		assertEquals(new HashSet<Class<?>>(expected), cls);
	}
}

class MyImplClass extends MyParentClass implements MyInt2 {
	@Override
	public int getData() {
		return super.getData() + 1;
	}
}

class MyParentClass implements MyInt1 {
	public int getData() {
		return 1;
	}
}

interface MyInt1 extends MyIntParent {
	public int getData();
}

interface MyInt2 extends MyIntParent {
	public int getData();
}

interface MyIntParent {
	public int getData();
}
