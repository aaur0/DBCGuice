package net.dbc.guice.tests.usinginterfaces;

import net.dbc.guice.Alias;
import net.dbc.guice.HasContract;
import net.dbc.guice.Invariant;
import net.dbc.guice.Postcondition;
import net.dbc.guice.Precondition;

@HasContract
@Invariant(
		value = "id > 0",
		desc = "Id to search for must be greater than 0."
) 
public interface CarRepository {
	
	@Precondition(
			value = "id > 0",
			desc = "Id to search for must be greater than 0."
	) 
	@Postcondition(
			value = "if (_returnValue_ != null) return _returnValue_.getId() == id",
			desc = "If any car found, then it must have the id given as search argument."
	) 
	public Car getById(@Alias("id") long id);
}
