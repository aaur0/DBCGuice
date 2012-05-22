package net.dbc.guice.tests.simple;

import net.dbc.guice.aop.ContractException;
import net.dbc.guice.tests.DbcTestCase;

public class ContractViolationCases extends DbcTestCase {

	PersonRepository personRepository;;

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

	public void testGetByHeight() {
		try {
			personRepository.getByHeight(180);
			fail("ContractException not thrown!");
		} catch (ContractException e) {
			System.out.println(e.getMessage());
		}
	}
}
