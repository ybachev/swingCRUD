package com.swingCRUD;

import java.util.Date;
import java.util.List;

import model.Person;
import service.PersonService;

public class App 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       
        PersonService personService = new PersonService();
        /*
		Person person1 = new Person(4, "Fyodor", "Dostoevsky", "M.", new Date(1966, 10, 8));
		Person person2 = new Person(8, "Geo", "Meo", "", new Date(1986, 10, 8));
		Person person3 = new Person(9, "Isto", "Nov", "I.", new Date(1969, 10, 8));
		
		System.out.println("*** Persist - start ***");
		personService.persist(person1);
		personService.persist(person2);
		personService.persist(person3);
		List<Person> personsLs1 = personService.findAll();
		System.out.println("Persons Persisted are :");
		for (Person p : personsLs1) {
			System.out.println("-" + p.toString());
		}
		System.out.println("*** Persist - end ***");
		
		System.out.println("*** Update - start ***");
		person1.setMiddleName("The Idiot");
		personService.update(person1);
		System.out.println("Person Updated is =>" +personService.findById(person1.getId()).toString());
		System.out.println("*** Update - end ***");
		
		
		System.out.println("*** Find - start ***");
		int id1 = person1.getId();
		Person another = personService.findById(id1);
		System.out.println("Person found with id " + id1 + " is =>" + another.toString());
		System.out.println("*** Find - end ***");
		
		
		System.out.println("*** Delete - start ***");
		int id3 = person3.getId();
		personService.delete(id3);
		System.out.println("Deleted person with id " + id3 + ".");
		System.out.println("Now all persons are " + personService.findAll().size() + ".");
		System.out.println("*** Delete - end ***");
		*/
		System.out.println("*** FindAll - start ***");
		List<Person> personsLs2 = personService.findAll();
		System.out.println("Persons found are :");
		for (Person p : personsLs2) {
			System.out.println("-" + p.toString());
		}
		System.out.println("*** FindAll - end ***");
		
		
		System.out.println("*** DeleteAll - start ***");
		/*personService.deleteAll();
		System.out.println("Persons found are now " + personService.findAll().size());
		System.out.println("*** DeleteAll - end ***");
		 System.exit(0);*/
	}
   
}
