package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Endpoint de saludo
    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello World";
    }

    // Endpoint para obtener todas las personas
    @GetMapping("/api/persons")
    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
