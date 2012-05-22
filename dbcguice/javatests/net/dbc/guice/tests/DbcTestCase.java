package net.dbc.guice.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.dbc.guice.module.BshDbCGuiceModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class DbcTestCase extends TestCase {
	
	Injector injector;
	
	public DbcTestCase() {
		List<Module> modules = new ArrayList<Module>();
		modules.add(new BshDbCGuiceModule());
		modules.add(new YourAppModule());
		this.injector = Guice.createInjector(modules);
	}
	
	public <T> T getInstanceFor(Class<T> aClass) {
		return this.injector.getInstance(aClass);
	}
}
