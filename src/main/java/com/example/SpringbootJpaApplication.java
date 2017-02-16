package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.model.Passport;
import com.example.model.Person;
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
        this.personRepository.save(person);
    }
}
