package be.abis.hello.service;

import org.springframework.stereotype.Service;

import be.abis.hello.model.Person;

@Service
public class abisHelloService implements HelloService{

	@Override
	public Person findPerson(int d) {
		Person p = new Person ("Griet");
		return p;
	}

}
