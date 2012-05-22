package net.dbc.guice.aop.contract;

import java.lang.annotation.Annotation;

import net.dbc.guice.Invariant;
import net.dbc.guice.NeverReturnsNull;
import net.dbc.guice.Postcondition;
import net.dbc.guice.Precondition;

/**
 * Defines the types of contracts available.
 * 
 * @author raul.bajales@gmail.com
 */
public enum ContractType {
	
	PRECONDITION("Precondition", Precondition.class),
	POSTCONDITION("Postcondition", Postcondition.class),
	INVARIANT("Invariant", Invariant.class),
	NEVERRETURNSNULL("NeverReturnsNull", NeverReturnsNull.class);
	
	private String name;
	private Class<? extends Annotation> type;

	private ContractType(String name, Class<? extends Annotation> type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}
	
	public Class<? extends Annotation> getType() {
		return this.type;
	}
}
