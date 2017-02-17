package com.example.utils;

import java.util.HashSet;
import java.util.Set;

import com.example.model.Passport;
import com.example.model.Person;
import com.example.model.Phone;
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
		
		Phone phone1 = new Phone("91","111");
		Phone phone2 = new Phone("91","222");
		Set<Phone> set = new HashSet<>();
		set.add(phone2);
		set.add(phone1);
		person.setPhones(set);
		//passport.setPerson(person);
		Gson gson = new GsonBuilder().create();
		gson.toJson(person,System.out);
	}

}
