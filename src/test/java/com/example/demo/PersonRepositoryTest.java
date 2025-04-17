package com.example.demo;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("Test save person with spring data jpa")
    public void testSave() {
        // Create a new Person object
        Person person = new Person();
        person.setName("Michael Scott");
        person.setAge(45);

        // Save the person to the database
        Person savedPerson = personRepository.save(person);

        Long personId = (long)savedPerson.getId();

        // Verify that the person was saved correctly
        assertNotNull(personRepository.findById(personId));

        // Print the saved person to the console
        System.out.println("Saved person: " + savedPerson);

        // Verify that the person was saved correctly
        assertNotNull(personRepository.findById(personId));
        assertEquals("Michael Scott", savedPerson.getName());
        assertEquals(45, savedPerson.getAge());
    }

    @Test
    @DisplayName("Test save and find all persons with spring data jpa")
    public void findAll() {
        List<Person> persons = List.of(
                new Person("Alice Smith", 25),
                new Person("Bob Johnson", 32),
                new Person("Charlie Brown", 28),
                new Person("Diana Prince", 35),
                new Person("Ethan Hunt", 40),
                new Person("Fiona Gallagher", 22),
                new Person("George Miller", 29),
                new Person("Hannah Davis", 27),
                new Person("Ian Wright", 33),
                new Person("Julia Roberts", 31)
        );

        // Save the person to the database
        personRepository.saveAll(persons);

        // Retrieve all persons from the database
        List<Person> personsFromDb = personRepository.findAll();

        // Verify that the list is not empty and contains the saved persons
        assertFalse(personsFromDb.isEmpty());

        // Print the persons to the console, expected Michael Scott in first position
        for (Person person : personsFromDb) {
            System.out.println(person);
        }

        // Verify that the first person in the list Michael Scott
        assertEquals("Michael Scott", personsFromDb.getFirst().getName());
        assertEquals(45,personsFromDb.getFirst().getAge());
    }

    @Test
    @DisplayName("Test delete person with spring data jpa")
    public void testDelete() {
        // Create a new Person object
        Person newPerson = new Person("John Doe", 30);
        personRepository.save(newPerson);

        Long personId = (long)newPerson.getId();

        // Verify that the person was saved correctly
        assertNotNull(personRepository.findById(personId));

        // Print the saved person to the console
        System.out.println("Saved person: " + newPerson);

        // Delete the person from the database
        personRepository.delete(newPerson);

        // Verify that the person was deleted
        assertFalse(personRepository.findById(personId).isPresent());
    }

    @Test
    @DisplayName("Test update person with spring data jpa")
    public void testUpdate() {
        // Create a new Person object
        Person person = new Person("Jane Doe", 25);
        personRepository.save(person);

        Long personId = (long)person.getId();

        // Verify that the person was saved correctly
        assertNotNull(personRepository.findById(personId));

        // Print the saved person to the console
        System.out.println("Saved person: " + person);

        Person updatedPerson = personRepository.findById(personId).get();

        // Verify that the person was saved correctly
        assertNotNull(updatedPerson);

        // Update the person's name and age
        updatedPerson.setName("Joan Doe");
        updatedPerson.setAge(35);
        personRepository.save(updatedPerson);

        // Verify that the person's name and age were updated
        Person updatedPersonDB = personRepository.findById(personId).orElse(null);
        assertNotNull(updatedPersonDB);
        assertEquals("Joan Doe", updatedPersonDB.getName());
        assertEquals(35, updatedPersonDB.getAge());

        // Print the updated person to the console
        System.out.println("Updated person: " + updatedPerson);
    }

}
