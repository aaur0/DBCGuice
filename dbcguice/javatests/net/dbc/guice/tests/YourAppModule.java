package net.dbc.guice.tests;

import net.dbc.guice.tests.usinginterfaces.CarRepository;
import net.dbc.guice.tests.usinginterfaces.CarRepositoryImpl;

import com.google.inject.Binder;
import com.google.inject.Module;

public class YourAppModule implements Module {

	public void configure(Binder binder) {
		binder.bind(CarRepository.class).to(CarRepositoryImpl.class);
	}
}
