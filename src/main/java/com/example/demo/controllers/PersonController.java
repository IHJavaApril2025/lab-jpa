package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/api/persons")
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, World!";
    }
}
