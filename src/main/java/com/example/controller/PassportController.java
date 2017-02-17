package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.ObjectNotFoundException;
import com.example.model.Passport;
import com.example.model.Person;
import com.example.service.PassportService;
import com.example.service.PersonService;

@RestController
public class PassportController {

    @Autowired
    private PersonService personServiceImpl;

    @Autowired
    private PassportService passportServiceImpl;
    
    @RequestMapping(value = "/passport", method = RequestMethod.GET)
    public List<Passport> passport() throws Exception {
        return this.passportServiceImpl.findAllPassports();
    }

    @RequestMapping(value = "/passport/{name}", method = RequestMethod.GET)
    public Passport person(@PathVariable String name) throws ObjectNotFoundException{
        return this.personServiceImpl.findPassportByName(name);
    }
    
    @RequestMapping(value = "/passport/num/{number}", method = RequestMethod.GET)
    public Person personByPassport(@PathVariable String number) throws ObjectNotFoundException{
        return this.passportServiceImpl.findPersonByNumber(number);
    }
    
    @ExceptionHandler({ObjectNotFoundException.class})
    void handleRecordNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), "Resource not found!!!");
    }
}
