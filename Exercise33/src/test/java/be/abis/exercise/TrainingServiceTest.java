package be.abis.exercise;

import be.abis.exercise.model.Session;
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

	@Test
	public void thereAre2SessionsForDB2BAS(){
		List<Session> sessions = trainingService.findSessionsForCourse("DB2BAS");
		assertEquals(2,sessions.size());
	}



	
	

}
