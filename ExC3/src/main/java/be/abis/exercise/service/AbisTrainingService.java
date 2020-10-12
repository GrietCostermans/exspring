package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;

@Service
public class AbisTrainingService implements TrainingService {
    
	@Autowired
	CourseService courseService;
	@Autowired
	PersonRepository personRepository;
	
	
	@Override
	public CourseService getCourseService() { 
		return courseService;	
	}

	public void setCourseService(CourseService courseService) { 
		this.courseService = courseService;
	}
	
	@Override
	public ArrayList<Person> getAllPersons() { return personRepository.getAllPersons();}

	@Override
	public Person findPerson(int id) {
		return personRepository.findPerson(id);
	}

	@Override
	public List<Course> showFollowedCourses(Person person) {
		// ik zie de link niet tussen courses en persons
		return null;
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		//nog niet implementeren?
		}

	@Override
	public void addPerson(Person person) throws IOException {
		personRepository.addPerson(person);
	}

	@Override
	public void deletePerson(int id) {
		personRepository.deletePerson(id);
	}
	
	@Override
	public Person findPerson(String emailAddress, String passWord) {
		try {
			return personRepository.findPerson(emailAddress, passWord);	}
		catch (NullPointerException e){
			System.out.println("nullpointer exception");
			return null;
			
		}
			
		}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		personRepository.changePassword(p, newPswd);	
		
	}

}
