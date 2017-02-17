package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.ObjectNotFoundException;
import com.example.model.Passport;
import com.example.model.Person;
import com.example.repository.PassportRepository;
import com.example.service.PassportService;

@Component
public class PassportServiceImpl implements PassportService {

    @Autowired
    private PassportRepository passportRepository;

    @Override
    public List<Passport> findAllPassports() {
        List<Passport> passports = new ArrayList<Passport>();
        this.passportRepository.findAll().forEach(passport -> passports.add(passport));
        passports.stream().filter(p -> p!=null);
        return passports;
    }

    @Override
    @Transactional
    public Person findPersonByNumber(String number) throws ObjectNotFoundException {
        Passport passport = this.passportRepository.findPersonByPassportNumber(number);
        if(passport == null){
            throw new ObjectNotFoundException("Invalid Passport Number");
        }
        System.out.println("-- Number --"+passport.getNumber());
        System.out.println("-- Person --"+passport.getPerson().getName());
        return passport.getPerson();
    }

}
