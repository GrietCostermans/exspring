package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class CourseAppController {
		
	@Autowired
	TrainingService trainingService; 
	
	@GetMapping("/")
	public String showLogin(Model model) {
		//Course course7900 = trainingService.getCourseService().findCourse(7900);
		model.addAttribute("Login",new Login());
		return "loginForm";	
	}
	/*
	@PostMapping("/loginForm")
	public String submitLogin(Model model, Login login) {
		// zoek naam op in person file
		//Person plogin = findperson();
		Person personLogged = trainingService.findPerson(login.getEmailAddress(),login.getPassword());
		
		// als ok dan
		//    return mainmenu
		// als nok dan
		//    return login
		return "loginForm";
	}*/
	
	
	
	
	
	
}