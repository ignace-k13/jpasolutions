package be.abis.exercise;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Session;
import be.abis.exercise.repository.EnrolmentJpaRepository;
import be.abis.exercise.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TrainingServiceTest {

	@Autowired
	TrainingService trainingService;

	@Autowired
	EnrolmentJpaRepository enrolmentJpaRepository;

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


	
	

}
