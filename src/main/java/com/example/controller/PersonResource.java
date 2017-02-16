package com.example.controller;

import org.springframework.hateoas.ResourceSupport;

import com.example.model.Passport;

/**
 * 
 * @author Sameer Shukla
 *
 * Decorating REST as Per HATEOAS principle creating links here.
 */
public class PersonResource extends ResourceSupport {

	private String name;

	private Passport passport;
	
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

	
}
