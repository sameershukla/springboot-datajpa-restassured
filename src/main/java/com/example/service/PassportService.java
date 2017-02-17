package com.example.service;

import java.util.List;

import com.example.exceptions.ObjectNotFoundException;
import com.example.model.Passport;
import com.example.model.Person;

public interface PassportService {

    public List<Passport> findAllPassports();
    
    public Person findPersonByNumber(String number) throws ObjectNotFoundException;
}
