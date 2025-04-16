package com.example.demo.repositories;

import com.example.demo.models.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("guardando una persona")
    public void testSave() {
        Person person = new Person("John Doe", 30);

        Person personSaved = personRepository.save(person);

        Optional<Person> personFoundOptional = personRepository.findById(personSaved.getId());

        if (personFoundOptional.isPresent()) {
            assertNotNull(personFoundOptional.get());
        }

        Person personFound = personFoundOptional.get();
        assertEquals("John Doe", personFound.getName());
        assertEquals(30, personFound.getAge());

        personRepository.deleteById(personFound.getId());
    }

    @Test
    @DisplayName("buscando todas las personas")
    public void testFindAll() {
        List<Person> personList = new ArrayList<>(
                Arrays.asList(
                        new Person("Ditto", 30),
                        new Person("Maria", 34),
                        new Person("Martina", 25),
                        new Person("Ana", 30),
                        new Person("Angela", 25),
                        new Person("Perico", 28)
                ));

        personRepository.saveAll(personList);

//        List<Person> personListFound = personRepository.findAll();
//        assertNotNull(personListFound);
//
//        System.out.println(Arrays.asList(personListFound));
//
//        assertEquals("Ditto", personListFound.get(0).getName());
//        assertEquals(30, personListFound.get(0).getAge());
//
//        personRepository.deleteAll();
    }

    @Test
    @DisplayName("delete person")
    public void testDelete() {
        Person person = new Person("John Doe", 30);

        Person personSaved = personRepository.save(person);

        Optional<Person> personFoundOptional = personRepository.findById(personSaved.getId());

        if (personFoundOptional.isPresent()) {
            assertNotNull(personFoundOptional.get());
        }

        Person personFound = personFoundOptional.get();

        personRepository.delete(personFound);

        Optional<Person> personDeletedOptional = personRepository.findById(personFound.getId());
        if (personDeletedOptional.isEmpty()) {
            //esto no se puede hacer
//            assertNull(personDeletedOptional.get()); // esto nos devolveria NoSuchElementException: No value present
        }

//        System.out.println(personDeletedOptional.get()); // esto tampoco se puede hacer porque no hay valor
//        assertNull(personDeletedOptional.get().getId());

    }

    @Test
    @DisplayName("update person")
    public void testUpdate(){
        Person person = new Person("Marcial", 25);

        Person personSaved = personRepository.save(person);

        Optional<Person> personFoundOptional = personRepository.findById(personSaved.getId());

        if(personFoundOptional.isPresent()){
            assertNotNull(personFoundOptional.get());
        }

        Person personFound = personFoundOptional.get();
        personFound.setName("Nuevo nombre");
        personFound.setAge(30);

        personRepository.save(personFound);

        personFoundOptional = personRepository.findById(personSaved.getId());
        if(personFoundOptional.isPresent()){
            assertNotNull(personFoundOptional.get());
        }

        personFound = personFoundOptional.get();
        System.out.println(personFound);

        assertNotNull(personFound.getId());
        assertEquals("Nuevo nombre", personFound.getName());
        assertEquals(30, personFound.getAge());

        personRepository.delete(personFound);
    }
}
