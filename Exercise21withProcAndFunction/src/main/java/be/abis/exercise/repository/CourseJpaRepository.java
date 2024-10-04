package be.abis.exercise.repository;

import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface CourseJpaRepository extends JpaRepository<Course,Integer> {

    Course findCourseByCourseId(int id);

    Course findCourseByShortTitleStartingWithIgnoreCase(String title);

    @Modifying
    @Query(value="update courses set caprice=:newprice where cid=:id", nativeQuery=true)
    void updatePrice(@Param("id") int id, @Param("newprice") double newPrice);

    @Procedure(procedureName = "update_courseprice")
    void updatePriceViaProcedure(@Param("course_id") int id, @Param("newprice") double newPrice);

    @Procedure(procedureName = "count_courses_by_price_and_days_viaproc")
    int numberOfCourseByPriceAndDays(@Param("price") double dayPrice, @Param("days") int numberOfDays);
}
