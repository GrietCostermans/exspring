package be.abis.exercise.controller;

import java.util.ArrayList;

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
	
	Person personLogged;
	
	@Autowired
	TrainingService trainingService; 
	
	@GetMapping("/")
	public String showLogin(Model model) {
		// is gemakkelijker
		Login defaultLogin = new Login("jdoe@abis.be","def456");
		//model.addAttribute("Login",new Login());
		model.addAttribute("Login",defaultLogin);
		return "loginForm";	
	}
	
	@PostMapping("/")
	public String submitLogin(Model model, Login login) {
		// zoek naam op in person file
		personLogged = trainingService.findPerson(login.getEmailAddress(),login.getPassword());
		
		// als ok dan
		//    return mainmenu
		// als nok dan
		//    return login
		return "redirect:/mainMenu";
	}
			
	@GetMapping("/mainMenu")
	public String showMainMenu(Model model) {
		model.addAttribute("person",personLogged);
		return "mainMenu";	
	}
	
	@PostMapping("/mainMenu")
	public String submitMainMenu(Model model) {
		//model.addAttribute("Login",new Login());
		return "mainMenu";	
	}
	
	
	
	@GetMapping("/Logout")
	public String gotoLoginAgain(Model model) {
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	@GetMapping("/personAdmin")
	public String showPersonAdmin(Model model) {
		return "personAdmin";	
	}
	
	@PostMapping("/personAdmin")
	public String submitPersonAdmin(Model model) {
		return "personAdmin";	
	}
	
	
	
	
	@GetMapping("/searchCourses")
	public String showSearchCourses(Model model) {
		return "searchCourses";	
	}
	
	@PostMapping("/searchCourses")
	public String submitSearchCourses(Model model) {
		return "searchCourses";	
	}
	
	
	@GetMapping("/listAllPersons")
	public String showListAllPersons(Model model, ArrayList<Person> persons) {
		ArrayList<Person> allPersons = trainingService.getAllPersons();
		model.addAttribute("persons",allPersons);
		return "listAllPersons";	
	}
	
	@PostMapping("/listAllPersons")
	public String submitListAllPersons(Model model) {
		return "listAllPersons";	
	}
	
	
	
	
	
	
	
	
	
}