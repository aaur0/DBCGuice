package net.dbc.guice.examples;

import net.dbc.guice.aop.ContractException;
import net.dbc.guice.examples.DbcTestCase;

public class ContractViolationCasesUsingInterfaces extends DbcTestCase {
	
	public void testGetById() {
		try {
			CarRepository carRepository = getInstanceFor(CarRepository.class);
			carRepository.getById(-1);
		} catch (ContractException e) {
			System.out.println(e.getMessage());
			return;
		}
		fail("ContractException not thrown!");
	}
}
