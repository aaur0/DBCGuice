package net.dbc.guice.examples;

import net.dbc.guice.aop.ContractException;

public class personex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//CarRepository carRepository = getInstanceFor(CarRepository.class);
			//carRepository.getById(-1);
		} catch (ContractException e) {
			System.out.println(e.getMessage());
		}	

	}

}
