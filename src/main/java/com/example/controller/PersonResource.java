package com.example.controller;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.example.model.Phone;

/**
 * 
 * @author Sameer Shukla
 *
 * Decorating REST as Per HATEOAS principle creating links here.
 */
public class PersonResource extends ResourceSupport {

	private String name;

	private List<Phone> phone;

	public List<Phone> getPhone() {
		return phone;
	}

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
