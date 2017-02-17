package com.example.controller;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.example.model.Passport;
import com.example.model.Phone;

/**
 * 
 * @author Sameer Shukla
 *
 *         Decorating REST as Per HATEOAS principle creating links here.
 */
public class PersonResource extends ResourceSupport {

    private String name;

    private Passport passport;

    private Set<Phone> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

}
