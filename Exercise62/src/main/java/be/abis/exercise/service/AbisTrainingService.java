package be.abis.exercise.service;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.mapper.EnrolmentMapper;
import be.abis.exercise.model.*;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.EnrolmentJpaRepository;
import be.abis.exercise.repository.SessionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbisTrainingService implements TrainingService {

    @Autowired
    CompanyJpaRepository companyRepository;

    @Autowired
    SessionJpaRepository sessionRepository;
    @Autowired
    EnrolmentJpaRepository enrolmentRepository;

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
    @Transactional(rollbackFor = EnrolException.class)
    public void enrolForSession(Person person, int sessionId) throws EnrolException {

        Person personToEnrol = null;
        try {
            personToEnrol = personService.addPerson(person);
        } catch (PersonAlreadyExistsException e) {
            personToEnrol = personService.findPerson(person.getEmailAddress());
        }

        System.out.println("person to enrol: " + personToEnrol.getPersonId());
        Session s = sessionRepository.findById(sessionId);
        //System.out.println("session found: " + s.getCourse().getLongTitle());
        if (s == null) throw new EnrolException("Can not enrol, session does not exist");
        if (this.countEnrolmentsForSession(s.getSessionId())>=12)throw new EnrolException("Session is full");
        if (this.findEnrolment(personToEnrol.getPersonId(),sessionId)!=null)throw new EnrolException("You are already enrolled for this session");
        Integer eno = enrolmentRepository.getLastEnoForSession(sessionId);
        int newEno = (eno==null)?1:eno+1;
        // double priceToPay = getCourseService().findCourse(s.getCourse().getCourseId()).getPricePerDay();
        Company c = personToEnrol.getCompany();
        Integer enrolmentCompanyNumber = c==null?null:c.getId();
        try {
            enrolmentRepository.saveEnrolment(s.getSessionId(), newEno, personToEnrol.getPersonId(), s.getCourse().getPricePerDay(), enrolmentCompanyNumber);
        } catch (DataAccessException dae){
            throw new EnrolException("oops, something went wrong");
        }
    }

    @Override
    public List<Session> findSessionsForCourse(String courseTitle) {
       return sessionRepository.findByCourseTitle(courseTitle) ;
    }

    @Override
    public List<EnrolmentDTO> findEnrolments(int personId) {
        List<Object[]> result = enrolmentRepository.findByEnrolleeNQ(personId);
        return result.stream().map(EnrolmentMapper::toDTO).collect(Collectors.toList()) ;
    }

    @Override
    @Transactional
    public void cancelSession(int sessionId) {
        sessionRepository.cancelSession(sessionId);
    }

    @Override
    public Integer countEnrolmentsForSession(int sessionId) {
        return enrolmentRepository.countEnrolmentsForSession(sessionId);
    }

    @Override
    public Enrolment findEnrolment(int personId, int sessionId) {
        return enrolmentRepository.findEnrolment(personId,sessionId);
    }

    @Transactional
    public Session addNewCompanySession(String courseTitle,int personId,int companyId, LocalDate startDate) throws PersonNotFoundException {
        Course course = courseService.findCourse(courseTitle);
        Person instructor = personService.findPerson(personId);
        Company location = companyRepository.findCompanyById(companyId);
        CompanySession cs = new CompanySession(startDate,instructor,location,course);
        Session newSession = sessionRepository.save(cs);
        return newSession;
    }
}
