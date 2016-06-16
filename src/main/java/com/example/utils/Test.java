package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Person;
import com.example.model.Phone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {

	public static void main(String[] args) {
		Person person = new Person();
		person.setName("Abc");
		
		Phone p1 = new Phone("9008007009","+91");
		Phone p2 = new Phone("9008007009","+91");
		
		List<Phone> phones = new ArrayList<>();
		phones.add(p1);
		phones.add(p2);
		person.setPhones(phones);
		Gson gson = new GsonBuilder().create();
		gson.toJson(person,System.out);
	}

}
