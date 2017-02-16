package com.example.utils;

import com.example.model.Passport;
import com.example.model.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {

	public static void main(String[] args) {
		Person person = new Person();
		person.setName("Abc");
		
		Passport passport = new Passport();
		passport.setCountry("India");
		passport.setNumber("YT449494");
		
		person.setPassport(passport);
		//passport.setPerson(person);
		Gson gson = new GsonBuilder().create();
		gson.toJson(person,System.out);
	}

}
