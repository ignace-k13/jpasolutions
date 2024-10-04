package be.abis.exercise;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonServiceTest {
	
	@Autowired
    PersonService personService;

	@Test
	@Order(1)
	public void person1ShouldBeCalledJohn() throws PersonNotFoundException {
		String firstName = personService.findPerson(1).getFirstName();
		assertEquals("John",firstName);
	}

	@Test
	@Order(2)
	public void thereShouldBe3PersonsInTheFile(){
		int nrOfPersons = personService.getAllPersons().size();
		assertEquals(3,nrOfPersons);
	}

	@Test
	@Order(3)
	public void addNewPerson() throws PersonAlreadyExistsException {
		Address a = new Address("Diestsevest","32","3000","Leuven","B");
		Company c = new Company("Abis","016/455610","BE12345678",a);
		Person p = new Person(4,"Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
		personService.addPerson(p);
	}

	@Test
	@Order(4)
	public void changePassWordOfAddedPerson() throws PersonNotFoundException {
		Person p = personService.findPerson("sschillebeeckx@abis.be","abis123");
		personService.changePassword(p,"blabla");
	}

	@Test
	@Order(5)
	public void deleteAddedPerson() throws PersonCanNotBeDeletedException {
		personService.deletePerson(4);
	}

	
	

}
