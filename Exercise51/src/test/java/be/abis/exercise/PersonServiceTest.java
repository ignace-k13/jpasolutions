package be.abis.exercise;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonServiceTest {
	
	@Autowired
    PersonService personService;

	@Autowired
	CompanyJpaRepository compRep;


	@Test
	public void person1ShouldBeCalledJAN() throws PersonNotFoundException {
		String firstName = personService.findPerson(1).getFirstName();
		assertEquals("JAN",firstName);
	}

	@Test
	public void person1000ShouldThrowException() {
		assertThrows(PersonNotFoundException.class, ()-> personService.findPerson(1000));
	}

	@Test
	public void thereShouldBe45PersonsInTheDB(){
		int nrOfPersons = personService.getAllPersons().size();
		assertEquals(45,nrOfPersons);
	}

	@Test
	public void person3ByEmailAndPassword() throws PersonNotFoundException {
		Person person = personService.findPerson("ann.dekeyser@abis.be","adk789");
		assertEquals(3,person.getPersonId());
	}

	@Test
	public void personWithWrongEmailPasswordShouldThrowException() {
		assertThrows(PersonNotFoundException.class, ()-> personService.findPerson("ann.dekeyser@abis.be","wrongpwd"));
	}

	@Test
	public void thereAre3PersonsForAbis()  {
		List<Person> persons = personService.findPersonsByCompanyName("ABIS");
		assertEquals(3,persons.size());
	}

	@Test
	@Transactional
	public void addNewPersonWithNewCompany() throws PersonAlreadyExistsException {
		Address a = new Address("Some Street","32","1000","BRUSSEL","B");
		Company c = new Company("BBIS","016/123455","BE12345678",a);
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
		long sizePersonsBefore = personService.count();
		long sizeCompaniesBefore= compRep.count();
		personService.addPerson(p);
		long sizePersonsAfter = personService.count();
		long sizeCompaniesAfter= compRep.count();
		assertEquals(1,sizePersonsAfter-sizePersonsBefore);
		assertEquals(1,sizeCompaniesAfter-sizeCompaniesBefore);
	}

	@Test
	@Transactional
	public void addNewPersonWithExistingCompany() throws PersonAlreadyExistsException {
		Address a = new Address("Diestsevest","32","3000","LEUVEN","B");
		Company c = new Company("ABIS N.V.","016/455610","BE12345678",a);
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
		long sizePersonsBefore = personService.count();
		long sizeCompaniesBefore= compRep.count();
		personService.addPerson(p);
		long sizePersonsAfter = personService.count();
		long sizeCompaniesAfter= compRep.count();
		assertEquals(1,sizePersonsAfter-sizePersonsBefore);
		assertEquals(0,sizeCompaniesAfter-sizeCompaniesBefore);
	}

	@Test
	@Transactional
	public void addNewPersonWithoutCompany() throws PersonAlreadyExistsException {
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl");
		long sizeBefore = personService.count();
		Person added = personService.addPerson(p);
		long sizeAfter = personService.count();
		assertEquals(1,sizeAfter-sizeBefore);
		assertTrue(added.getCompany()==null);
	}
	@Test
	//@Transactional
	public void addAlreadyExistingPersonShouldThrowException()  {
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),
				"ann.dekeyser@abis.be","abis123","nl");
		assertThrows(PersonAlreadyExistsException.class, ()-> personService.addPerson(p));
	}

	@Test
	@Transactional
	public void changePassWordOfPerson3() throws PersonNotFoundException {
		Person p = personService.findPerson(3);
		Person updated =personService.changePassword(p,"blabla");
		assertEquals("blabla",updated.getPassword());
	}

	@Test
	@Transactional
	public void deletePerson92WorksForComp20() throws PersonCanNotBeDeletedException {
		long sizeBefore = personService.count();
		personService.deletePerson(92);
		long sizeAfter = personService.count();
		assertEquals(-1,sizeAfter-sizeBefore);
	}

	@Test
	//@Transactional //rollback should already be handled by PersonService
	public void deleteNonExistingPersonShouldThrowException()  {
		PersonCanNotBeDeletedException pcnbde = assertThrows(PersonCanNotBeDeletedException.class, ()->personService.deletePerson(1000));
		assertEquals("Person cannot be deleted since he does not exist.",pcnbde.getMessage());
	}

	@Test
	//@Transactional //rollback should already be handled by PersonService
	public void deletePersonWithLinksShouldThrowException()  {
		PersonCanNotBeDeletedException pcnbde = assertThrows(PersonCanNotBeDeletedException.class, ()->personService.deletePerson(3));
		assertEquals("You cannot delete this person, since there are still enrolments/sessions for him.",pcnbde.getMessage());
	}
	

}
