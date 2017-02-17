package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exceptions.ObjectNotFoundException;
import com.example.model.Passport;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Iterable<? extends Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonById(Long id) {
        return this.personRepository.findOne(id);
    }

    @Override
    public Person savePerson(Person entity) {
        return this.personRepository.save(entity);
    }

    @Override
    public Passport findPassportByName(String name) throws ObjectNotFoundException{
        Person person = this.personRepository.findByName(name);
        if(person == null){
            throw new ObjectNotFoundException("Invalid Person Name");
        }
        return person.getPassport();
    }

}
