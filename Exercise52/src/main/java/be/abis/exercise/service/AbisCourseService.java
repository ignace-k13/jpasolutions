package be.abis.exercise.service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbisCourseService implements CourseService {

    @Autowired
    CourseJpaRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourse(int id) {
        return courseRepository.findCourseByCourseId(id);
    }

    @Override
    public Course findCourse(String shortTitle) {
        return courseRepository.findCourseByShortTitleStartingWithIgnoreCase(shortTitle);
    }

    @Override
    public Course addCourse(Course c) {
        return courseRepository.save(c);
    }

    @Override
    public Course updateCourse(Course c) {
        Course course = courseRepository.findCourseByCourseId(c.getCourseId());
        Course updated=null;
        if (course!=null) {
            course=c;
            updated =courseRepository.save(course);
        }
        return updated;
    }


    @Override
    @Transactional
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
        courseRepository.flush();
    }

    @Override
    public Long countAllCourses() {
        return courseRepository.count();
    }


}
