package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.TrainingService;

@Controller
public class CourseAppController {
		
	@Autowired
	TrainingService trainingService; 
	
	@GetMapping("/")
	public String showTitle7900(Model model) {
		
		Course course7900 = trainingService.getCourseService().findCourse(7900);
		model.addAttribute("course",course7900.getLongTitle());
		return "course";	
	}
}