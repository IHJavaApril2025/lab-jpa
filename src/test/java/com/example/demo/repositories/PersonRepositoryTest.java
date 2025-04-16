package com.example.demo.repositories;

import com.example.demo.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave() {
        Person newPerson = new Person("John Doe", 30);
        newPerson = personRepository.save(newPerson);

        Optional<Person> OptPerson = personRepository.findById(newPerson.getId());
        assertTrue(OptPerson.isPresent());
        Person person = OptPerson.get();

        System.out.println(person);
        assertNotNull(person.getId());
        assertEquals("John Doe", person.getName());
        assertEquals(30, person.getAge());
    }

    @Test
    public void testFindAll() {
        Person newPerson = new Person("John Doe", 30);
        Person newPerson2 = new Person("Jane Doe", 30);
        List<Person> peopleSaved = new ArrayList<>();
        peopleSaved.add(newPerson);
        peopleSaved.add(newPerson2);
        personRepository.saveAll(peopleSaved);

        List<Person> allPeople = personRepository.findAll();

        assertFalse(allPeople.isEmpty());
        assertNotNull(allPeople);

        Person person = allPeople.get(0);
        System.out.println(person);
        assertEquals("John Doe", person.getName());
        assertEquals(30, person.getAge());
    }
@Test
    public void testDelete() {
    Person newPerson = new Person("John Doe", 30);
    newPerson = personRepository.save(newPerson);

    Optional<Person> OptPerson = personRepository.findById(newPerson.getId());
    assertTrue(OptPerson.isPresent());
    Person person = OptPerson.get();
   personRepository.delete(person);

    OptPerson = personRepository.findById(newPerson.getId());
    assertFalse(OptPerson.isPresent());

    System.out.println(person);


    assertEquals("John Doe", person.getName());
    assertEquals(30, person.getAge());
}

@Test
    public void testUpdate() {
        Person newPerson = new Person("John Doe", 30);
        newPerson = personRepository.save(newPerson);
        Optional<Person> OptPerson = personRepository.findById(newPerson.getId());
        assertTrue(OptPerson.isPresent());
        Person person = OptPerson.get();
        person.setName("Jane Doe");
        person.setAge(25);
        personRepository.save(person);
        OptPerson = personRepository.findById(newPerson.getId());
        assertTrue(OptPerson.isPresent());
        System.out.println(person);
        assertNotNull(person.getId());
        assertEquals("Jane Doe", person.getName());
        assertEquals(25, person.getAge());
}

}
