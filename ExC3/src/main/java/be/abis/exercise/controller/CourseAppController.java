package be.abis.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@Controller
public class CourseAppController {
	
	Person personLogged;
	String shownMessage;
	
	@Autowired
	TrainingService trainingService; 
	@Autowired
	CourseService courseService;
	
	@GetMapping("/")
	public String showLogin(Model model, Login login) {
		// is gemakkelijker
		//Login defaultLogin = new Login("jdoe@abis.be","def456");
		model.addAttribute("Login",new Login());
		//model.addAttribute("login",defaultLogin);
		return "login";	
	}
	
	
			
	@GetMapping("/welcome")
	public String showMainMenu(Model model) {
		model.addAttribute("person",personLogged);
		return "welcome";	
	}
	
	@PostMapping("/welcome")
	public String submitMainMenu(Model model) {
		return "welcome";	
	}
	
	@GetMapping("/Logout")
	public String gotoLoginAgain(Model model) {
		return "redirect:/";
	}
	
	@GetMapping("/gotowelcome")
	public String gotowelcome(Model model) {
		return "redirect:/welcome";
	}
	
	@GetMapping("/gotopersonadmin")
	public String gotopersonadmin(Model model) {
		return "redirect:/personadmin";
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
		shownMessage = "";
		return "personchangepassword";	
	}
	
	@PostMapping("/personchangepassword")
	public String submitchangepassword(Model model,Login login) {
		try {
			trainingService.changePassword(personLogged, login.getPassword()) ;
			shownMessage = "Password Changed";
			
		} catch (IOException e) {
			e.printStackTrace();
			shownMessage = "Houston you have a problem";
		}
		model.addAttribute("outputmessage",shownMessage);
		personLogged.setPassword(login.getPassword());
		model.addAttribute("login",new Login());		
		return "personchangepassword";	
	}
		
	@GetMapping("/personaddnew")
	public String showpersonaddnew(Model model, Person person) {
		model.addAttribute("Login",new Person());
		model.addAttribute("outputmessage","");
		return "personaddnew";	
	}
	
	@PostMapping("/personaddnew")
	public String submitpersonaddnew(Model model,@Valid Person person, BindingResult bindingResult ) {
		if  (bindingResult.hasErrors()) {
			return "personaddnew";
		}
		
		try {
			trainingService.addPerson(person);
			shownMessage =  "Person added nr : " + person.getPersonId(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("outputmessage",shownMessage);	
		return "personaddnew";
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
		shownMessage =  "Person deleted nr : " + person.getPersonId(); 
		model.addAttribute("outputmessage",shownMessage);	
		return "persondelete";	
	}
	
	
			
	@GetMapping("/courseadmin")
	public String showSearchCourses(Model model) {
		return "courseadmin";	
	}
	
	@PostMapping("/courseadmin")
	public String submitSearchCourses(Model model) {
		return "courseadmin";	
	}
	
	
	@GetMapping("/courselistall")
	public String showListAllCourses(Model model, ArrayList<Course> courses) {
		List<Course> allCourses = courseService.findAllCourses();
		model.addAttribute("courses",allCourses);
		return "courselistall";	
	}
	
	@PostMapping("/courselistall")
	public String submitListAllCourses(Model model) {
		return "courselistall";	
	}
	
	@GetMapping("/courselistbyid")
	public String showListCourseByID(Model model) {
		model.addAttribute("course",new Course());
		model.addAttribute("coursefound",new Course());		
		return "courselistbyid";	
	}
	
	@PostMapping("/courselistbyid")
	public String submitlistCourseByID(Model model,Course course) {
		model.addAttribute("course",course);
		model.addAttribute("coursefound",courseService.findCourse(Integer.parseInt(course.getCourseId())));
		return "courselistbyid";	
	}
	
	@PostMapping("/")
	public String submitLogin(Model model, Login login, BindingResult bindingResult) {
		
		personLogged = trainingService.findPerson(login.getEmailAddress(),login.getPassword());
		System.out.println("personLogged "+personLogged );
		if  (personLogged == null) {
			bindingResult.reject("emailAddress", "This emailaddress is unknown in our db");
			//result.reject("email", "Login failed, try again");
			System.out.println("bindingResult.rejectValue");
            return "login" ;
		} else { 
			return "redirect:/welcome";
			
		}
	}
	
	/*
	private boolean validateEmailAddress(Login valLogin) {
		Person personToValidate = trainingService.findPerson(valLogin.getEmailAddress(), valLogin.getPassword());
		System.out.println("personToValidate.getEmailAddress()"+ personToValidate.getEmailAddress());
		if (personToValidate.getEmailAddress().equals(null)) {
			return false;
			}
		return true;
	}
	*/
	
	
}