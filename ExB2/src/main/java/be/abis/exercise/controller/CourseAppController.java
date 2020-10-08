package be.abis.exercise.controller;

import java.io.IOException;
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
		model.addAttribute("login",defaultLogin);
		return "login";	
	}
	
	@PostMapping("/")
	public String submitLogin(Model model, Login login) {
		personLogged = trainingService.findPerson(login.getEmailAddress(),login.getPassword());
		return "redirect:/welcome";
	}
			
	@GetMapping("/welcome")
	public String showMainMenu(Model model) {
		model.addAttribute("person",personLogged);
		return "welcome";	
	}
	
	@PostMapping("/welcome")
	public String submitMainMenu(Model model) {
		//model.addAttribute("Login",new Login());
		return "welcome";	
	}
	
	@GetMapping("/Logout")
	public String gotoLoginAgain(Model model) {
		return "redirect:/";
	}
	
	@GetMapping("/personadmin")
	public String showPersonAdmin(Model model) {
		return "personadmin";	
	}
	
	@PostMapping("/personadmin")
	public String submitPersonAdmin(Model model) {
		return "personadmin";	
	}
	
	@GetMapping("/personlistbyid")
	public String showListPersonByID(Model model) {
		model.addAttribute("person",new Person());
		model.addAttribute("personfound",new Person());		
		return "personlistbyid";	
	}
	
	@PostMapping("/personlistbyid")
	public String submitlistPersonByID(Model model,Person person) {
		model.addAttribute("person",person);
		model.addAttribute("personfound",trainingService.findPerson(person.getPersonId()));
		return "personlistbyid";	
	}
	
	@GetMapping("/personlistall")
	public String showListAllPersons(Model model, ArrayList<Person> persons) {
		ArrayList<Person> allPersons = trainingService.getAllPersons();
		model.addAttribute("persons",allPersons);
		return "personlistall";	
	}
	
	@PostMapping("/personlistall")
	public String submitListAllPersons(Model model) {
		return "personlistall";	
	}
	
		@GetMapping("/personchangepassword")
	public String showchangepassword(Model model) {
		model.addAttribute("login",new Login ());
		return "personchangepassword";	
	}
	
	@PostMapping("/personchangepassword")
	public String submitchangepassword(Model model,Login login) {
		
		try {
			trainingService.changePassword(personLogged, login.getPassword()) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		personLogged.setPassword(login.getPassword());
		model.addAttribute("login",personLogged);		
		return "personchangepassword";	
	}
		
	@GetMapping("/personaddnew")
	public String showpersonaddnew(Model model, Person person) {
		model.addAttribute("Login",new Person());
		return "personaddnew";	
	}
	
	@PostMapping("/personaddnew")
	public String submitpersonaddnew(Model model, Person person) {
		try {
			trainingService.addPerson(person);
			return "redirect:/personadmin";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "personaddnew";
		}
		//return "personaddnew";
	}
	
	@GetMapping("/persondelete")
	public String showPersonToDelete(Model model) {
		model.addAttribute("person",new Person());
		return "persondelete";	
	}
	
	@PostMapping("/persondelete")
	public String submitPersonToDelete(Model model,Person person) {
		int personToDelete = person.getPersonId();
		trainingService.deletePerson(personToDelete);
		model.addAttribute("person",person);
		return "redirect:/personadmin";	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	@GetMapping("/searchcourses")
	public String showSearchCourses(Model model) {
		return "searchcourses";	
	}
	
	@PostMapping("/searchcourses")
	public String submitSearchCourses(Model model) {
		return "searchcourses";	
	}
	
	
	
	
}