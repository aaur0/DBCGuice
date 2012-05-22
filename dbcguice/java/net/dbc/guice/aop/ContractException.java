package net.dbc.guice.aop;

import net.dbc.guice.Invariant;
import net.dbc.guice.Postcondition;
import net.dbc.guice.Precondition;

/**
 * This exception is thrown when any condition evaluated in the contract
 * returns false. Also a custom message is available to describe the violated contract.
 * @see Precondition
 * @see Postcondition
 * @see Invariant
 * 
 * @author raul.bajales@gmail.com
 */
@SuppressWarnings("serial")
public class ContractException extends RuntimeException {
	
	public static final String MESSAGE_HEADER = "Contract violated!";

	public ContractException(String message) {
		super("\n" + MESSAGE_HEADER + ((message != null && !message.equals("")) ? "\n" + message : ""));		
	}
}
