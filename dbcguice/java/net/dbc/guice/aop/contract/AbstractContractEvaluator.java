package net.dbc.guice.aop.contract;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;



import com.ucsb.cs.racelab.contracts.JavaClient;
import com.ucsb.cs.racelab.contracts.ContractValidator;

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
		for (ContractDescriptor descriptor : descriptors){			
			System.out.println("Condition to be evaluated : " +  descriptor.getEval());
			System.out.println("Method signature " +  descriptor.getMethodSignature());
			System.out.println("Context " +  descriptor.getContext().toString());
			System.out.println("Contract Type " +  descriptor.getContractType().toString());
			//System.out.println("ConMap " +  descriptor.getConMap().toString());
			//Thrift: The following code block is for thrift 		
			JavaClient client = new JavaClient("simple"); // instantiates a thrift client
			//Map<String,String> objects = new LinkedHashMap<String, Object>();
			Map<String,Object> obj = descriptor.getContext();
			// populate the object map			
			//for (String key : obj.keySet()){				
				//objects.put(key, obj.get(key));			
			try {
				client.validate(obj, descriptor.getConMap());
			} catch (TException e) {			
				ContractException ex = new ContractException(e.getMessage());
				throw ex;
			}			
			client.close();		
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
