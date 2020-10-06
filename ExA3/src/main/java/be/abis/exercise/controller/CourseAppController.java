package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;

@Controller
public class CourseAppController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/")
	public String showTitle7900(Model model) {
		
		Course course7900 = courseService.findCourse(7900); //  .getLongTitle()
		model.addAttribute("course",course7900);
		return "course";	
		
		
	}
	

}
