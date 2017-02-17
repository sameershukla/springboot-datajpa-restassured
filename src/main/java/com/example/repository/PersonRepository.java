package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Person;

/**
 * 
 * @author Sameer Shukla
 *
 *         Represnts DAO for Person Table
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    public Person findByName(String name);

}
