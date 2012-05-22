package net.dbc.guice.aop.contract.bsh;

import java.util.Map;

import net.dbc.guice.aop.ContractException;
import net.dbc.guice.aop.contract.AbstractContractEvaluator;
import net.dbc.guice.aop.contract.ContractDescriptor;
import bsh.EvalError;
import bsh.Interpreter;

/**
 * Implementation of ContractEvaluator that uses BeanShell as interpreter.
 * 
 * @author raul.bajales@gmail.com
 */
public class BshContractEvaluator extends AbstractContractEvaluator {

	@Override
	protected boolean doEvaluate(ContractDescriptor descriptor) {
		Interpreter bsh = new Interpreter();
		boolean retValue = true;
		Object eval;
		try {
			setBshContext(bsh, descriptor.getContext());
			eval = bsh.eval(descriptor.getEval());
		} catch (EvalError e) {
			throw new RuntimeException(e);
		}
		if (eval == null) {
			throw new ContractException(
					"BeanShell expression evaluated to null: '"
							+ descriptor.getEval() + "'");
		}
		if (eval.getClass().isAssignableFrom(Boolean.class))
			retValue = ((Boolean) eval).booleanValue();
		return retValue;
	}

	/**
	 * Sets the BeanShell context.
	 * 
	 * @param bsh
	 *            The BeanShell interpreter.
	 * @param context
	 *            The context
	 * @throws EvalError
	 *             When the context cannot be set.
	 */
	private void setBshContext(Interpreter bsh, Map<String, Object> context)
			throws EvalError {
		if (context == null)
			return;
		for (String alias : context.keySet())
			bsh.set(alias, context.get(alias));
	}
}
