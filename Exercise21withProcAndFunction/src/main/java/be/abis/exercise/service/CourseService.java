package be.abis.exercise.service;

import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseService {

    public List<Course> findAllCourses();
    public Course findCourse(int id);
    public Course findCourse(String shortTitle);
    public Course addCourse(Course c);
    public Course updateCourse(Course c);
    public void deleteCourse(int id);

    Long countAllCourses();

    void updatePrice(int id, double newPrice);

    void updatePriceViaProcedure(int id,double newPrice);

    int numberOfCourseByPriceAndDays(double dayPrice,  int numberOfDays);

}
