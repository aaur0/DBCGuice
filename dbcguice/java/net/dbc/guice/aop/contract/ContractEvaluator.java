package net.dbc.guice.aop.contract;

import java.util.List;

import net.dbc.guice.aop.ContractException;

/**
 * All the contract evaluators must implement this interface. Typically, there
 * will be one implementation for each interpreter (i.e. BeanShell, Ruby, OGNL).
 * 
 * @author raul.bajales@gmail.com
 */
public interface ContractEvaluator {

	/**
	 * Performs the evaluation.
	 * 
	 * @param descriptors
	 *            Contains the list of contract descriptors, each of them
	 *            including the evaluation to be made and his context.
	 * @throws ContractException
	 *             when any of the evaluated contracts returns false.
	 */
	public void evaluate(List<ContractDescriptor> descriptors)
			throws ContractException;
}
