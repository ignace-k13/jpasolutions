package be.abis.exercise.service;

import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisTrainingService implements TrainingService {

    @Value("Welcome to the Abis Training Service")
    private String welcomeMessage;

    @Autowired
    private PersonService personService;

    @Autowired
    private CourseService courseService;

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public CourseService getCourseService() {
        return courseService;
    }


    @Override
    public void enrolForSession(Person person, int sessionId) throws EnrolException {

    }

    @Override
    public List<Session> findSessionsForCourse(String courseTitle) {
        return null;
    }



    @Override
    public List<Enrolment> findEnrolments(int personId) {
        return null;
    }


}
