package be.abis.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.hello.model.Person;
import be.abis.hello.service.HelloService;

@Controller
public class AppController {
	
	@Autowired
	HelloService helloService;
	
	@GetMapping("/") 
	public String showPerson(Model model) {
		
		Person p = helloService.findPerson(5);
		model.addAttribute("person",p);  //wanneer je naar getmapping / url gaat, zet die deze in de html
		return "Hello";  //zoekt in templates de naam + 	.html	
	}

}
