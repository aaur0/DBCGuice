package net.dbc.guice.tests.simple;

import java.util.ArrayList;
import java.util.List;

import net.dbc.guice.Alias;
import net.dbc.guice.HasContract;
import net.dbc.guice.NeverReturnsNull;
import net.dbc.guice.Postcondition;
import net.dbc.guice.Precondition;

@HasContract
public class PersonRepository {
	
	@Precondition("arg0 > 0")
	public Person getById(long id) {
		return new Person();
	}

	@Precondition(
	    value = "name != null && !name.equals(\"\") && age > 0",
	    desc = "When finding by name and age, name cannot be null or empty, and age must be greater than 0."
	)
	public List<Person> findByNameAndAge(@Alias("name") String name, @Alias("age") int age) {
		return new ArrayList<Person>();
	}
	
	@Precondition(
		    value = "example != null && !example.getName().equals(\"\") && example.getAge() > 0",
		    desc = "When finding by example, name cannot be null or empty, and age must be greater than 0."
		)
	public List<Person> findByExample(@Alias("example") Person example) {
		return new ArrayList<Person>();
	}

	@Precondition(
		    value = "name != null && !name.equals(\"\")",
		    desc = "When getting by name, the provided name cannot be null or empty."
	)
	@Postcondition(
		    value = "_returnValue_ != null ? _returnValue_.getName().equals(name) : true",
		    desc = "When getting by name the returned Person (if not null) must have the name that were searched for."
		)
	public Person getByName(@Alias("name") String name) {
		return new Person("Rolo");
	}
	
	@NeverReturnsNull
	public Person getByHeight(int height) {
		return null;
	}
}
