package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Phone;

/**
 * 
 * @author Sameer Shukla
 * 
 *         Represents DAO for Phone Table
 */
public interface PhoneRepository extends CrudRepository<Phone, Long> {

}
