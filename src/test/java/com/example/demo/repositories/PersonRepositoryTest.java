package com.example.demo.repositories;

import com.example.demo.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave() {
        // Create a person
        Person person = new Person("Jose", 30);

        // Save the person
        Person savedPerson = personRepository.save(person);

        // Find the person by id
        Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());

        // Print the person
        System.out.println("Found person: " + foundPerson.get());

        // Assertions
        assertTrue(foundPerson.isPresent());
        assertNotNull(foundPerson.get().getId());
        assertEquals("Jose", foundPerson.get().getName());
        assertEquals(30, foundPerson.get().getAge());
    }

    @Test
    public void testFindAll() {
        // Create a list of persons
        List<Person> persons = Arrays.asList(
                new Person("Ditto", 30),
                new Person("Jane Smith", 25)
        );

        // Save all persons
        List<Person> savedPersons = personRepository.saveAll(persons);

        // Find all persons
        List<Person> foundPersons = personRepository.findAll();

        // Print all persons
        System.out.println("All persons: " + foundPersons);

        // Check that we have at least 2 persons (there might be more from previous tests)
        assertTrue(foundPersons.size() >= 2);

        // Find our saved persons in the list
        boolean foundDitto = false;
        boolean foundJane = false;

        for (Person person : foundPersons) {
            if ("Ditto".equals(person.getName()) && person.getAge() == 30) {
                foundDitto = true;
            }
            if ("Jane Smith".equals(person.getName()) && person.getAge() == 25) {
                foundJane = true;
            }
        }

        // Assertions
        assertTrue(foundDitto, "Should find Ditto in the database");
        assertTrue(foundJane, "Should find Jane Smith in the database");
    }

    @Test
    public void testDelete() {
        // Create a person
        Person person = new Person("To Be Deleted", 40);

        // Save the person
        Person savedPerson = personRepository.save(person);
        Long personId = savedPerson.getId();

        // Find the person by id
        Optional<Person> foundPerson = personRepository.findById(personId);

        // Ensure the person exists
        assertTrue(foundPerson.isPresent());

        // Delete the person
        personRepository.delete(foundPerson.get());

        // Try to find the person again
        Optional<Person> deletedPerson = personRepository.findById(personId);

        // Print the result
        System.out.println("Deleted person exists: " + deletedPerson.isPresent());

        // Assert that the person is deleted
        assertFalse(deletedPerson.isPresent());
    }

    @Test
    public void testUpdate() {
        // Create a person
        Person person = new Person("Original Name", 20);

        // Save the person
        Person savedPerson = personRepository.save(person);
        Long personId = savedPerson.getId();

        // Find the person by id
        Optional<Person> foundPerson = personRepository.findById(personId);

        // Ensure the person exists
        assertTrue(foundPerson.isPresent());

        // Update the person
        Person personToUpdate = foundPerson.get();
        personToUpdate.setName("Nuevo nombre");
        personToUpdate.setAge(30);

        // Save the updated person
        Person updatedPerson = personRepository.save(personToUpdate);

        // Find the updated person
        Optional<Person> foundUpdatedPerson = personRepository.findById(personId);

        // Print the updated person
        System.out.println("Updated person: " + foundUpdatedPerson.get());

        // Assertions
        assertTrue(foundUpdatedPerson.isPresent());
        assertNotNull(foundUpdatedPerson.get().getId());
        assertEquals("Nuevo nombre", foundUpdatedPerson.get().getName());
        assertEquals(30, foundUpdatedPerson.get().getAge());
    }
}
