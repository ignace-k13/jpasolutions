package be.abis.exercise.repository;

import be.abis.exercise.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session,Integer> {

    Session findById(int id);

    @Query(value = "select * \n" +
            "from sessions inner join persons on sins_pno=pno\n" +
            "              inner join companies on sloc_cono=cono\n" +
            "              inner join courses on s_cid = cid\n" +
            "where upper(cstitle) like upper(concat(:title,'%')) and scancel is null",
            nativeQuery = true)
    List<Session> findByCourseTitle(@Param("title") String title);

    @Modifying
    @Query(value = "update sessions set scancel = 'c' where sno = :sessionId", nativeQuery = true)
    void cancelSession(@Param("sessionId") int sessionId);

    @Query(value = "select skind from sessions where sno=:sessionId", nativeQuery = true)
    String findSkindValue(@Param("sessionId") int sessionId);

}
