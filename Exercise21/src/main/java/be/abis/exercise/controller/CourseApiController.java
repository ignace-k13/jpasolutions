package be.abis.exercise.controller;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseApiController {

    @Autowired
    CourseService courseService;

    @GetMapping("")
    public List<Course> findAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping("{id}")
    public Course printCourse(@PathVariable("id") int myId){
        Course c = courseService.findCourse(myId);
        return c;
    }

    @GetMapping("/query")
    public Course findCourseByTitle(@RequestParam("title") String shortTitle){
        Course c = courseService.findCourse(shortTitle);
        return c;
    }

    @PostMapping("")
    public Course addCourse(@RequestBody Course course){
      return courseService.addCourse(course);
    }

    @PutMapping("{id}")
    public Course updateCourse(@RequestBody Course course, @PathVariable("id") int id){
        course.setCourseId(id);
        return courseService.updateCourse(course);
    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable("id") int id){
        courseService.deleteCourse(id);
    }

}
