package com.example.service;

import com.example.exceptions.ObjectNotFoundException;
import com.example.model.Passport;
import com.example.model.Person;

public interface PersonService {

    public Iterable<? extends Person> findAllPersons();

    public Person findPersonById(Long id);

    public Person savePerson(Person entity);

    public Passport findPassportByName(String name) throws ObjectNotFoundException;
}
