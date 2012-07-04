package net.dbc.guice.examples;

import net.dbc.guice.aop.ContractException;
import net.dbc.guice.examples.DbcTestCase;

public class ContractViolationCases extends DbcTestCase {

	PersonRepository personRepository;
	@Override
	protected void setUp() throws Exception {
		personRepository = getInstanceFor(PersonRepository.class);
	}
	public void testGetById() {
		try {
			personRepository.getById(-1);
			fail("ContractException not thrown!");
		} catch (ContractException e) {
			System.out.println(e.getMessage());
		}
	}
	public void testFindByNameAndAge() {
		try {
			personRepository.findByNameAndAge(null, 0);
			fail("ContractException not thrown!");
		} catch (ContractException e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetByName() {
		try {
			personRepository.getByName("Pedro");
			fail("ContractException not thrown!");
		} catch (ContractException e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetByNameWithNullName() {
		try {
			personRepository.getByName(null);
			fail("ContractException not thrown!");
		} catch (ContractException e) {
			System.out.println(e.getMessage());
		}
	}	
}
