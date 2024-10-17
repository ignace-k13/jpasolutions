package be.abis.exercise;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.*;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.EnrolmentJpaRepository;
import be.abis.exercise.repository.SessionJpaRepository;
import be.abis.exercise.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TrainingServiceTest {

	@Autowired
	TrainingService trainingService;

	@Autowired
	EnrolmentJpaRepository enrolmentJpaRepository;
	@Autowired
	SessionJpaRepository sessionRepository;
	@Autowired
	CompanyJpaRepository companyRepository;


	@Test
	public void thereIs1NonCancelledSessionForDB2BAS(){
		List<Session> sessions = trainingService.findSessionsForCourse("DB2BAS");
		assertEquals(1,sessions.size());
	}

	@Test
	public void thereAre3NonCancelledEnrolmentForPerson25(){
		List<EnrolmentDTO> enrolments = trainingService.findEnrolments(25);
		assertEquals(3,enrolments.size());
	}

	@Test
	public void findEnrolmentByIdViaQueryDSL(){
		Enrolment enrolment = enrolmentJpaRepository.findBySession_SessionIdAndEnrolmentInSession(1,1);
		assertEquals(25,enrolment.getEnrollee().getPersonId());
	}

	@Test
	public void findEnrolmentByIdViaOwnQuery(){
		Enrolment enrolment = enrolmentJpaRepository.findEnrolmentById(1,1);
		assertEquals(25,enrolment.getEnrollee().getPersonId());
	}

	@Test
	@Transactional
	public void cancelSession1() {
		trainingService.cancelSession(1);
		assertTrue(sessionRepository.findById(1).isCancelled());
	}

	@Test
	@Transactional
	public void correctEnrolmentForNewPerson() throws EnrolException {
		Address a = new Address("Some Street","32","1000","BRUSSEL","B");
		Company c = new Company("BBIS","016/123455","BE12345678",a);
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
		int sizeEnrolmentsBefore = trainingService.countEnrolmentsForSession(1);
		long personsBefore = trainingService.getPersonService().count();
		long companiesBefore = companyRepository.count();
		trainingService.enrolForSession(p,1);
		int sizeEnrolmentsAfter = trainingService.countEnrolmentsForSession(1);
		long personsAfter = trainingService.getPersonService().count();
		long companiesAfter = companyRepository.count();
		assertEquals(1,sizeEnrolmentsAfter-sizeEnrolmentsBefore);
		assertEquals(1,personsAfter-personsBefore);
		assertEquals(1,companiesAfter-companiesBefore);
	}

	@Test
	@Transactional
	public void correctEnrolmentForNewPersonWithoutCompany() throws EnrolException {
		Person p = new Person("Jean","Dupont", LocalDate.of(1971,01,18),"jean.dupont@hotmail.com","mypassword","fr");
		int sizeEnrolmentsBefore = trainingService.countEnrolmentsForSession(1);
		long personsBefore = trainingService.getPersonService().count();
		trainingService.enrolForSession(p,1);
		int sizeEnrolmentsAfter = trainingService.countEnrolmentsForSession(1);
		long personsAfter = trainingService.getPersonService().count();
		assertEquals(1,sizeEnrolmentsAfter-sizeEnrolmentsBefore);
		assertEquals(1,personsAfter-personsBefore);
	}

	@Test
	@Transactional
	public void correctEnrolmentForExistingPerson() throws PersonNotFoundException, EnrolException {
		Person p = trainingService.getPersonService().findPerson(1);
		int sizeEnrolmentsBefore = trainingService.countEnrolmentsForSession(5);
		long personsBefore = trainingService.getPersonService().count();
		trainingService.enrolForSession(p,5);
		int sizeEnrolmentsAfter = trainingService.countEnrolmentsForSession(5);
		long personsAfter = trainingService.getPersonService().count();
		assertEquals(1,sizeEnrolmentsAfter-sizeEnrolmentsBefore);
		assertEquals(0,personsAfter-personsBefore);
	}

	@Test
	//@Transactional
	public void enrolmentRolledBackForUnknownSession() throws PersonNotFoundException {
		Address a = new Address("Some Street","32","1000","BRUSSEL","B");
		Company c = new Company("BBIS","016/123455","BE12345678",a);
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
		assertThrows(EnrolException.class, ()->trainingService.enrolForSession(p,20));
	}

	@Test
	//@Transactional
	public void person25alreadyEnrolledInSession1() throws PersonNotFoundException {
		Person p= trainingService.getPersonService().findPerson(25);
		assertThrows(EnrolException.class, ()->trainingService.enrolForSession(p,1));
	}

}
