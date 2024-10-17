package be.abis.exercise.repository;

import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.EnrolmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrolmentJpaRepository extends JpaRepository<Enrolment, EnrolmentId> {

    Enrolment findBySession_SessionIdAndEnrolmentInSession(int sessionId, int enrolmentInSession);

    @Query("select e from Enrolment e where e.session.sessionId=:sessionId and e.enrolmentInSession = :enrolmentInSession")
    Enrolment findEnrolmentById(@Param("sessionId") int sessionId, @Param("enrolmentInSession") int enrolmentInSession);


    List<Object[]> findByEnrolleeNQ(@Param("pno") int personId);
}
