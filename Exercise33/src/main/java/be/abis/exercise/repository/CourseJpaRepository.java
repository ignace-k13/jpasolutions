package be.abis.exercise.repository;

import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<Course,Integer> {

    Course findCourseByCourseId(int id);

    Course findCourseByShortTitleStartingWithIgnoreCase(String title);
}
