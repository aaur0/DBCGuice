package net.dbc.guice.examples;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.dbc.guice.Alias;
import net.dbc.guice.HasContract;
import net.dbc.guice.NeverReturnsNull;
import net.dbc.guice.Postcondition;
import net.dbc.guice.Precondition;

@HasContract
public class PersonRepository {	
	

	
	@Precondition("id:float,>0")
	public Person getById(long id) {
		return new Person();
	}
	@Precondition(value = "lambda s: isinstance(name, str) and len(name)>0")
	public List<Person> findByNameAndAge(String name, int age) {
		return new ArrayList<Person>();
	}
	
	@Postcondition(
		    value = "_returnValue_ != null ? _returnValue_.getName().equals(name) : true",
		    desc = "When getting by name the returned Person (if not null) must have the name that were searched for."
		)
	public Person getByName(@Alias("name") String name) {
		return new Person("Rolo");
	}
	public static void main(String[] args) {
		Person person = new Person("anand");
		person.setAge(25);		
		PersonRepository pr = new PersonRepository();
		pr.getById(-1);
		pr.findByNameAndAge("anand", 10);		
	}
}
