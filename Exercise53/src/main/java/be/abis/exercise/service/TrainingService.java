package be.abis.exercise.service;

import be.abis.exercise.dto.EnrolmentDTO;
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

    List<Session>  findSessionsForCourse(String courseTitle);

    List<EnrolmentDTO> findEnrolments(int personId);

    void cancelSession(int sessionId);

    Integer countEnrolmentsForSession(int sessionId);

    Enrolment findEnrolment(int personId, int sessionId);
}
