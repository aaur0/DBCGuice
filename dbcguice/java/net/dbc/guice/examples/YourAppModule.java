package net.dbc.guice.examples;

import net.dbc.guice.examples.CarRepository;
import net.dbc.guice.examples.CarRepositoryImpl;

import com.google.inject.Binder;
import com.google.inject.Module;

public class YourAppModule implements Module {

	public void configure(Binder binder) {		
		binder.bind(CarRepository.class).to(CarRepositoryImpl.class);
	}
}
