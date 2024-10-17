package be.abis.exercise.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cono")
	private int id;
	@Column(name="coname")
	private String name;
	@Column(name="cotel")
	private String telephoneNumber;
	@Column(name="covat")
	private String vatNr;
	@Embedded
	private Address address;

	@OneToMany(targetEntity = Person.class, mappedBy = "company")
	@JsonManagedReference
	private List<Person> employees = new ArrayList<>();

	public Company(){}
	public Company(String name, String telephoneNumber, String vatNr, Address address) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.vatNr = vatNr;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getVatNr() {
		return vatNr;
	}
	public void setVatNr(String vatNr) {
		this.vatNr = vatNr;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Person> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Person> employees) {
		this.employees = employees;
	}

	public void addEmployee(Person person){
		employees.add(person);
		person.setCompany(this);
	}

	public void removeEmployee(Person person){
		employees.remove(person);
		person.setCompany(null);
	}

	public String toString(){
		return name + " in " + address.getTown();
	}
	

}
