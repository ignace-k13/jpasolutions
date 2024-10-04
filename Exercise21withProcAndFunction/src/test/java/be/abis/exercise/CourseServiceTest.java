package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseServiceTest {
	
	@Autowired
    CourseService courseService;
	
	@Test
	public void course7900isWorkshopSQL() {
		Course c = courseService.findCourse(7900);
		assertEquals("SQLWS",c.getShortTitle().toUpperCase().trim());
	}

	@Test
	public void sizeListIs24(){
		List<Course> allCourses = courseService.findAllCourses();
		assertEquals(24,allCourses.size());
	}

	@Test
	public void courseSQLWStakes3Days() {
		String shortTitle = "SQLWS";
		Course found = courseService.findCourse(shortTitle);
		assertEquals(3,found.getNumberOfDays());
	}

	@Test
	@Transactional
	public void addCourse() {
		Course c = new Course("SPRINGJPA","Using JPA with Spring Boot",3,525.0);
		long sizeBefore = courseService.countAllCourses();
		courseService.addCourse(c);
		long sizeAfter = courseService.countAllCourses();
		assertEquals(1,sizeAfter-sizeBefore);
		//rollback
	}

	@Test
	@Transactional
	public void updateCourse() {
		Course c = new Course("IMSADFII", "Using IMSADFII",4,530.45);
		c.setCourseId(7800);
		courseService.updateCourse(c);
		assertEquals("Using IMSADFII", courseService.findCourse(7800).getLongTitle());
	}

	@Test
	@Transactional
	public void deleteCourse8055WhichHasNoChildren() {
		long sizeBefore = courseService.countAllCourses();
		int id = 8055;
		courseService.deleteCourse(id);
		long sizeAfter = courseService.countAllCourses();
		assertEquals(-1,sizeAfter-sizeBefore);
	}

	@Test
	//@Transactional
	public void updatePrice(){
		courseService.updatePrice(7900,123.45);
	}

	@Test
	//@Transactional
	public void updatePriceViaProcedure() {
		courseService.updatePriceViaProcedure(7850,456.78);
	}

	@Test
	public void numberOfCourseByPriceAndDays() {
		int nbr= courseService.numberOfCourseByPriceAndDays(550,3);
		assertEquals(6,nbr);
	}

	
	

}
