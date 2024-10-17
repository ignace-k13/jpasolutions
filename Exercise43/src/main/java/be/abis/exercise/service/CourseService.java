package be.abis.exercise.service;

import be.abis.exercise.model.Course;

import java.util.List;

public interface CourseService {

    public List<Course> findAllCourses();
    public Course findCourse(int id);
    public Course findCourse(String shortTitle);
    public Course addCourse(Course c);
    public Course updateCourse(Course c);
    public void deleteCourse(int id);

    Long countAllCourses();

}
