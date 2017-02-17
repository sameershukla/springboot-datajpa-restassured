package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Passport;

@Transactional
public interface PassportRepository extends CrudRepository<Passport, Long>
{

    @Query("Select p from Passport p where p.number=:number")
    public Passport findPersonByPassportNumber(@Param("number") String number);
}
