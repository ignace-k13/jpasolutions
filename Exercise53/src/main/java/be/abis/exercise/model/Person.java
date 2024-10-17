package be.abis.exercise.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "persons")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pno")
	private int personId;
	@Column(name = "pfname")
	private String firstName;
	@Column(name = "plname")
	private String lastName;
	@Column(name = "pbirthdate")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	@Column(name = "pemail")
	private String emailAddress;
	@Column(name = "ppass")
	private String password;
	@Column(name = "plang")
	private String language;

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "pa_cono")
	private Company company;

	public Person() {
	}

	public Person(String firstName, String lastName, LocalDate birthDate, String emailAddress, String password, String language) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.password = password;
		this.language = language;
	}

	public Person(String firstName, String lastName, LocalDate birthDate, String emailAddress, String password, String language, Company company) {
		this(firstName, lastName, birthDate, emailAddress, password, language);
		this.company = company;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Person with id " + personId + ", " + firstName + " " + lastName.trim() + ", works for " + company.getName().trim() + " in " + company.getAddress().getTown();
	}

}
