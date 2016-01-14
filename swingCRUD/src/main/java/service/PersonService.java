package service;

import java.util.List;

import DAO.PersonDAO;
import model.Person;

public class PersonService {

	private static PersonDAO personDao;

	public PersonService() {
		personDao = new PersonDAO();
	}

	public void persist(Person entity) {
		personDao.openCurrentSessionwithTransaction();
		personDao.persist(entity);
		personDao.closeCurrentSessionwithTransaction();
	}

	public void update(Person entity) {
		personDao.openCurrentSessionwithTransaction();
		personDao.update(entity);
		personDao.closeCurrentSessionwithTransaction();
	}

	public Person findById(Integer id) {
		personDao.openCurrentSession();
		Person Person = personDao.findById(id);
		personDao.closeCurrentSession();
		return Person;
	}

	public void delete(Integer id) {
		personDao.openCurrentSessionwithTransaction();
		Person Person = personDao.findById(id);
		personDao.delete(Person);
		personDao.closeCurrentSessionwithTransaction();
	}

	public List<Person> findAll() {
		personDao.openCurrentSession();
		List<Person> Persons = personDao.findAll();
		personDao.closeCurrentSession();
		return Persons;
	}

	public void deleteAll() {
		personDao.openCurrentSessionwithTransaction();
		personDao.deleteAll();
		personDao.closeCurrentSessionwithTransaction();
	}

	public PersonDAO personDao() {
		return personDao;
	}

	
}
