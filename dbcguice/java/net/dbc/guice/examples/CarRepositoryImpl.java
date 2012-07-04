package net.dbc.guice.examples;

public class CarRepositoryImpl implements CarRepository {
	
	public Car getById(long id) {
		
		return new Car(-1);
	}
}

