package com.swingCRUD;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Person;
import service.PersonService;

public class App 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) throws ParseException
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	PersonService personService = new PersonService();
    	System.out.println( "Hello World!" );
     
        /*
        
    	String dateInString = "1982-08-31";
    	Date date = sdf.parse(dateInString);
    	System.out.println(date); 
        Person pe = new Person("Change", "Change", "Change", date);
        personService.persist(pe);
        System.out.println( pe);
        */
        
        
		Person person1 = new Person("Grigor", "Dimitrov", "Haskovo", sdf.parse("1987-09-17"));
		Person person2 = new Person("Alan", "John", null, sdf.parse("1977-09-17"));
		Person person3 = new Person("Petar", "Ikonomov", "I.", sdf.parse("1997-09-17"));
		
		
		System.out.println("*** Persist - start ***");
		personService.persist(person1);
		personService.persist(person2);
		personService.persist(person3);
		
		
	      /*
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
		
		/*
		System.out.println("*** DeleteAll - start ***");
		
		personService.deleteAll();
		System.out.println("Persons found are now " + personService.findAll().size());
		System.out.println("*** DeleteAll - end ***");
		 System.exit(0);*/
	}
   
}
