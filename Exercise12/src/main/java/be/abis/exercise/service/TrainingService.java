package be.abis.exercise.service;

import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.Session;

import java.util.List;

public interface TrainingService {

    public String getWelcomeMessage();
    public PersonService getPersonService();
    public CourseService getCourseService();


    public void enrolForSession(Person person, int sessionId) throws EnrolException;

    List<Session> findSessionsForCourse(String courseTitle);

    public List<Enrolment> findEnrolments(int personId);


}
