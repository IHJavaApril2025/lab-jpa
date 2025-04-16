package com.jpaLab.jpaLab.controllers;

import com.jpaLab.jpaLab.Models.Person;
import com.jpaLab.jpaLab.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping("/person")
    public List<Person> allPerson() {
        return personRepository.findAll();
    }



}
