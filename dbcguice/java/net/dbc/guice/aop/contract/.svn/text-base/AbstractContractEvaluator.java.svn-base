package net.dbc.guice.aop.contract;

import java.util.List;

import net.dbc.guice.aop.ContractException;

/**
 * Convenience abstract implementation for the contract evaluator, it ensures
 * that the contract descriptors are valid before being processed.
 * 
 * @see ContractEvaluator
 * 
 * @author raul.bajales@gmail.com
 */
public abstract class AbstractContractEvaluator implements ContractEvaluator {

	/**
	 * Checks descriptors before delegating to the evaluator implementation.
	 */
	public void evaluate(List<ContractDescriptor> descriptors)
			throws ContractException {
		if (descriptors == null || descriptors.isEmpty())
			return;
		for (ContractDescriptor descriptor : descriptors) 
			if (descriptor.isValid() && !doEvaluate(descriptor)) {
				throw new ContractException(descriptor.toString());
			}
	}

	/**
	 * Inherited classes must implement this method that evaluates the contract
	 * using the desired interpreter (i.e. Ruby, OGNL, BeanShell).
	 * 
	 * @param descriptor
	 *            The contract descriptor to be used for the evaluation.
	 * @see ContractDescriptor
	 */
	protected abstract boolean doEvaluate(ContractDescriptor descriptor);
}
