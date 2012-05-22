package net.dbc.guice.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.dbc.guice.HasContract;
import net.dbc.guice.Postcondition;
import net.dbc.guice.Precondition;
import net.dbc.guice.aop.contract.ContextConstants;
import net.dbc.guice.aop.contract.ContractDescriptor;
import net.dbc.guice.aop.contract.ContractEvaluator;
import net.dbc.guice.aop.contract.ContractType;
import net.dbc.guice.module.BshDbCGuiceModule;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

/**
 * This intercept must be applied in those methods that has defined any
 * contract for those classes that has the HasContract annotation.
 * 
 * @see HasContract
 * @see Precondition
 * @see Postcondition
 * @see ContractType
 * 
 * @author raul.bajales@gmail.com
 */
public class DbcInterceptor implements MethodInterceptor {
	
	static Map<Method, ContractSet> contractSetCache = new HashMap<Method, ContractSet>();

	protected ContractEvaluator evaluator;

	/**
	 * Note that the evaluator implementation must be injected via Guice, this
	 * is done by the default module provided, BshDbCGuiceModule.
	 * 
	 * @param evaluator
	 *            The contract evaluator implementation.
	 * @see ContractEvaluator
	 * @see BshDbCGuiceModule
	 */
	@Inject
	public DbcInterceptor(ContractEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	/**
	 * Here the intercept is applied, delegating contract evaluation to the
	 * evaluator.
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		ContractSet contractSet = buildContractSetFor(invocation);
		evaluator.evaluate(contractSet.getPreconditions());
		Object returnValue = invocation.proceed();
		evaluator.evaluate(contractSet.getInvariants());
		for (ContractDescriptor descriptor : contractSet.getPostconditions())
			descriptor.getContext().put(ContextConstants.RETURN_VALUE_ALIAS, returnValue);
		evaluator.evaluate(contractSet.getPostconditions());
		return returnValue;
	}

	/**
	 * Builds a ContractSet instance for the given invocation and cache it if it is not yet cached,
	 * otherwise returns the cached instance.
	 * 
	 * @param invocation The MethodInvocation.
	 * @return The cached or created ContractSet instance.
	 */
	private ContractSet buildContractSetFor(MethodInvocation invocation) {
		ContractSet retValue = contractSetCache.get(invocation.getMethod());
		if (retValue != null)
			return retValue;
		List<ContractDescriptor> preconditions = ContractDescriptor.buildListFor(invocation,
				ContractType.PRECONDITION);
		List<ContractDescriptor> invariants = ContractDescriptor.buildListFor(invocation,
				ContractType.INVARIANT);
		List<ContractDescriptor> postconditions = ContractDescriptor.buildListFor(
				invocation, ContractType.POSTCONDITION);
		postconditions.addAll(ContractDescriptor.buildListFor(
				invocation, ContractType.NEVERRETURNSNULL));
		retValue = new ContractSet(preconditions, postconditions, invariants);
		contractSetCache.put(invocation.getMethod(), retValue);
		return retValue;
	}
	
	/**
	 * A contract set is define by a set of preconditions, postconditions and invariants 
	 * related to a certain method signature. 
	 * 
	 * @author raul.bajales@gmail.com
	 *
	 */
	class ContractSet {

		private List<ContractDescriptor> preconditions;
		private List<ContractDescriptor> postconditions;
		private List<ContractDescriptor> invariants;

		public ContractSet(List<ContractDescriptor> preconditions,
				List<ContractDescriptor> postconditions,
				List<ContractDescriptor> invariants) {
			this.preconditions = preconditions;
			this.postconditions = postconditions;
			this.invariants = invariants;
		}

		public List<ContractDescriptor> getPostconditions() {
			return this.postconditions;
		}

		public List<ContractDescriptor> getInvariants() {
			return this.invariants;
		}

		public List<ContractDescriptor> getPreconditions() {
			return this.preconditions;
		}
		
	}
}
