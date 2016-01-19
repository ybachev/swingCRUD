package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_person")
public class Person {

	private int id;

	private String firstName;
	private String lastName;
	private String middleName;
	private Date birthDate;

	public Person() {

	}

	public Person(int id, String firstName, String lastName, String middleName, Date birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.birthDate = birthDate;
	}

	@Id
	//@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String BirthDateFormated() {
		return new SimpleDateFormat("yyyy-MM-dd").format(birthDate);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", middleName=" + getMiddleName()
				+ ", birthDate=" + BirthDateFormated() + "]";
	}

}
