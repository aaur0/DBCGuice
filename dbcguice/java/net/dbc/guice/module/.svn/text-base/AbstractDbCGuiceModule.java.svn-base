package net.dbc.guice.module;

import net.dbc.guice.aop.DbcInterceptor;
import net.dbc.guice.aop.contract.ContractEvaluator;
import net.dbc.guice.util.DbCMatchers;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Abstract class to configure the contract evaluator implementation. Inherit from this
 * class to create a Guice module that configures the interceptor to use any 
 * implementation of ContractEvaluator different than the one provided (BshDbCGuiceModule).
 * 
 * @see ContractEvaluator
 * @see BshDbCGuiceModule
 * 
 * @author raul.bajales@gmail.com
 */
public class AbstractDbCGuiceModule implements Module {

	private ContractEvaluator contractEvaluator;

	public AbstractDbCGuiceModule(ContractEvaluator contractEvaluator) {
		super();
		this.contractEvaluator = contractEvaluator;
	}
	
	public void configure(Binder binder) {
		binder.bindInterceptor(DbCMatchers.hasContractInClassHierarchy(),
				DbCMatchers.hasContractInMethodHierarchy(), new DbcInterceptor(
						this.contractEvaluator));
	}
}
