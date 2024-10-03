package be.abis.exercise.repository;

import be.abis.exercise.model.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryCourseRepository implements CourseRepository {
	
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	
	public MemoryCourseRepository(){
		courses.add(new Course(7850,"DB2BAS","DB2, an overview",5,550.0));
		courses.add(new Course(7900,"SQLWS","Workshop SQL",3,475.0));
		courses.add(new Course(8000,"JAVAPROG","Java Programming",5,500.0));
		courses.add(new Course(8050,"MAVEN","Maven",1,450.0));
		courses.add(new Course(8100,"SPRING","Programming with Spring",3,525.0));
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	
	@Override
	public List<Course> findAllCourses() {
		return courses;
	}

	@Override
	public Course findCourse(int id) {
		return courses.stream().filter(c->c.getCourseId()==id).findFirst().orElse(null);
	}

	@Override
	public Course findCourse(String shortTitle) {
		return courses.stream().filter(c->c.getShortTitle().equals(shortTitle)).findFirst().orElse(null);
	}



}
