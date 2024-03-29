package net.dbc.guice.examples;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Person {
	
	private String name;
	private int age;

	public Person(String name) {
		this.name = name;
	}

	public Person() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
