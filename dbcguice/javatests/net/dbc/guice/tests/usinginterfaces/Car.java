package net.dbc.guice.tests.usinginterfaces;

public class Car {
	
	private long id;

	public Car(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
