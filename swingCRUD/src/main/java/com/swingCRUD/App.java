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
        
        PersonService bookService = new PersonService();
		Person person1 = new Person(5, "Fyodor", "Dostoevsky", "M.", new Date(1966, 10, 8));
		Person person2 = new Person(6, "Geo", "Balev", "", new Date(1986, 10, 8));
		Person person3 = new Person(7, "Hristo", "Ivanov", "I.", new Date(1969, 10, 8));
		System.out.println("*** Persist - start ***");
		bookService.persist(person1);
		bookService.persist(person2);
		bookService.persist(person3);
		List<Person> books1 = bookService.findAll();
		System.out.println("Persons Persisted are :");
		for (Person b : books1) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** Persist - end ***");
		System.out.println("*** Update - start ***");
		person1.setMiddleName("The Idiot");
		bookService.update(person1);
		System.out.println("Person Updated is =>" +bookService.findById(person1.getId()).toString());
		System.out.println("*** Update - end ***");
		System.out.println("*** Find - start ***");
		int id1 = person1.getId();
		Person another = bookService.findById(id1);
		System.out.println("Person found with id " + id1 + " is =>" + another.toString());
		System.out.println("*** Find - end ***");
		System.out.println("*** Delete - start ***");
		int id3 = person3.getId();
		bookService.delete(id3);
		System.out.println("Deleted book with id " + id3 + ".");
		System.out.println("Now all books are " + bookService.findAll().size() + ".");
		System.out.println("*** Delete - end ***");
		System.out.println("*** FindAll - start ***");
		List<Person> books2 = bookService.findAll();
		System.out.println("Persons found are :");
		for (Person b : books2) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** FindAll - end ***");
		System.out.println("*** DeleteAll - start ***");
		/*bookService.deleteAll();
		System.out.println("Persons found are now " + bookService.findAll().size());
		System.out.println("*** DeleteAll - end ***");
		 System.exit(0);*/
	}
   
}
