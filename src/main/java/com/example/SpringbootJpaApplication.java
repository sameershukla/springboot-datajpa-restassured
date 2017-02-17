package com.example;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.model.Passport;
import com.example.model.Person;
import com.example.model.Phone;
import com.example.repository.PersonRepository;


/**
 * 
 * @author Sameer Shukla
 *
 *         Boostrap Spring
 */

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    /**
     * You can initiate insertion here for demo.
     */
    @Override
    public void run(String... arg0) throws Exception {
        Person person = new Person();
        person.setName("SAMEER SHUKLA");
        Passport passport = new Passport();
        passport.setCountry("INDIA");
        passport.setNumber("RUR3939393");
        person.setPassport(passport);
        passport.setPerson(person);
        Phone phone1 = new Phone("91", "9939393");
        phone1.setPerson(person);
        Phone phone2 = new Phone("91", "993939311");
        phone2.setPerson(person);
        Set<Phone> phones = new HashSet<Phone>();
        phones.add(phone1);
        phones.add(phone2);
        person.setPhones(phones);
        this.personRepository.save(person);
    }
}
