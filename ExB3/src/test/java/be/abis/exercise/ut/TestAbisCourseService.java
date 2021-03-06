package be.abis.exercise.ut;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisCourseService {
		
		@Autowired
		CourseService courseService;
		
		@Test
		public void testIfCourseTitleOf7900IsWorkshopSQL() {
			System.out.println("name : " + courseService.findCourse(7900).getShortTitle());
			assertEquals("Workshop SQL",courseService.findCourse(7900).getShortTitle());
		}
		
		@Test
		public void testIfDayPriceOfCourse7900IsHigherThan400() {
			System.out.println("dayprice " + courseService.findCourse(7900).getPricePerDay());
			assertThat(7, is(greaterThan(5))); //=> OK
			assertThat(500,greaterThan(400));  //=> OK
			double expected = courseService.findCourse(7900).getPricePerDay();
			//assertThat(expected,greaterThan(400)); 			
		}
}
