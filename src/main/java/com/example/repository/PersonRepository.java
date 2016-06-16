package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Person;
import com.example.model.Phone;

/**
 * 
 * @author Sameer Shukla
 *
 *         Represnts DAO for Person Table
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

	public List<Phone> findByName(String name);

}
