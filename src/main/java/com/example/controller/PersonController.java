package com.example.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.example.exceptions.ObjectNotFoundException;
import com.example.model.Person;
import com.example.service.PersonService;

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
    private PersonService personService;

    /**
     * Fetch all the persons with Telephones in the System
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<PersonResource> persons() throws Exception {
        // Ensuring that no other object should be added in the collection.
        Iterable<? extends Person> persons = this.personService.findAllPersons();
        List<PersonResource> resources = new ArrayList<PersonResource>();
        for (Person person : persons) {
            PersonResource resource = new PersonResource();
            resource.setName(person.getName());
            resource.setPassport(person.getPassport());
            resource.setPhones(person.getPhones());
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
        Person person = this.personService.findPersonById(id);
        if (person == null) {
            throw new ObjectNotFoundException("No Person Found");
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
    HttpEntity<Resource<Person>> create(@RequestBody Person person) throws DataIntegrityViolationException, Exception {
        Person savedEntity = this.personService.savePerson(person);
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
     * 
     * @param response
     * @throws IOException
     */
    @ExceptionHandler({ IllegalArgumentException.class, ObjectNotFoundException.class })
    void handleRecordNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), "Resource not found!!!");
    }

    /**
     * Handle Conflicts
     * 
     * @param response
     * @throws IOException
     */
    @ExceptionHandler({ DataIntegrityViolationException.class })
    void handleConflicts(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(), "Duplicate Record Found");
    }
}
