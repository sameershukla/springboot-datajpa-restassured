package com.example.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Person;
import com.example.repository.PersonRepository;

/**
 * 
 * @author Sameer Shukla
 *
 *         Controller....
 */

@RestController
@Transactional
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	/**
	 * Fetch all the persons with Telephones in the System
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public List<PersonResource> persons() throws Exception {
		Iterable<? extends Person> persons = this.personRepository.findAll();
		List<PersonResource> resources = new ArrayList<PersonResource>();
		for (Person person : persons) {
			PersonResource resource = new PersonResource();
			resource.setName(person.getName());
			resource.setPassport(person.getPassport());
			Link detail = linkTo(PersonController.class).slash(person.getId()).withSelfRel();
			resource.add(detail);
			resources.add(resource);
		}
		return resources;
	}

	/**
	 * Fetch the person with the given id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public PersonResource person(@PathVariable Long id) throws Exception {
		PersonResource resource = null;
		Person person = personRepository.findOne(id);
		if (person == null) {
			throw new IllegalArgumentException();
		} else {
			resource = new PersonResource();
			resource.setName(person.getName());
			resource.setPassport(person.getPassport());
			Link detail = linkTo(PersonController.class).slash(person.getId()).withSelfRel();
			resource.add(detail);
		}
		return resource;
	}

	/**
	 * Create Person
	 * 
	 * @param person
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	HttpEntity<Resource<Person>> create(@RequestBody Person person) throws Exception {
		Person savedEntity = this.personRepository.save(person);
		Resource<Person> resource = new Resource<Person>(savedEntity);
		resource.add(linkTo(methodOn(PersonController.class).person(savedEntity.getId())).withRel("person"));
		return new ResponseEntity<Resource<Person>>(resource, HttpStatus.OK);
	}

	/**
	 * Handle All exceptions, just for the demo purpose
	 * 
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(Exception.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Please try again");
	}

	/**
	 * Handle All 404 scenarios
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	void handleRecordNotFound(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), "Resource not found!!!");
	}
}
