package be.abis.exercise.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.MemoryCourseRepository;

@Service
public class AbisCourseService implements CourseService {

	@Autowired
	MemoryCourseRepository memoryCourseRepository; 
	
	@Override
	public List<Course> findAllCourses() {
		return memoryCourseRepository.findAllCourses();
	}

	@Override
	public Course findCourse(int id) {
		return memoryCourseRepository.findCourse(id);
	}

	@Override
	public Course findCourse(String shortTitle) {
		return memoryCourseRepository.findCourse(shortTitle);
	}
	

}
