package be.abis.exercise.repository;

import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.EnrolmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrolmentJpaRepository extends JpaRepository<Enrolment, EnrolmentId> {

    Enrolment findBySession_SessionIdAndEnrolmentInSession(int sessionId, int enrolmentInSession);

    @Query("select e from Enrolment e where e.session.sessionId=:sessionId and e.enrolmentInSession = :enrolmentInSession")
    Enrolment findEnrolmentById(@Param("sessionId") int sessionId, @Param("enrolmentInSession") int enrolmentInSession);


    List<Object[]> findByEnrolleeNQ(@Param("pno") int personId);

    @Query("select max(e.enrolmentInSession)  from Enrolment e where e.session.sessionId = :sessionId ")
    Integer getLastEnoForSession(@Param("sessionId") int sessionId);

    @Modifying
    @Query(value = "insert into enrolments (e_sno,eno, e_pno,epay,e_cono) values (:sesid, :eno, :persno, :epay, :compno)", nativeQuery = true)
    void saveEnrolment(@Param("sesid") int sesid, @Param("eno") int eno, @Param("persno") int persno, @Param("epay") double epay,@Param("compno") Integer compno);

    @Query("select count(e.enrollee)  from Enrolment e where e.session.sessionId = :sessionId ")
    Integer countEnrolmentsForSession(int sessionId);

    @Query("select e from Enrolment e where e.enrollee.personId = :personId and e.session.sessionId=:sessionId")
    Enrolment findEnrolment(int personId,int sessionId);
}
