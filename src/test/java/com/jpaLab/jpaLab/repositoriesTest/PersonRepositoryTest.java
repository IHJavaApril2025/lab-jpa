package com.jpaLab.jpaLab.repositoriesTest;

import com.jpaLab.jpaLab.Models.*;
import com.jpaLab.jpaLab.repositories.PersonRepository;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("test save")
    public void testSave() {
        Person person = new Person("Anastasio", 96);
        Person savedPerson = personRepository.save(person);
        Optional<Person> p1 = personRepository.findById(person.getId());
        System.out.println(p1);
        assertNotNull(p1);
        assertSame("Anastasio", person.getName());
        assertSame(96, person.getAge());
    }

    @Test
    @DisplayName("testFindAll()")
    public void testFindAll() {
        List<Person> person = new ArrayList<>();
        person.add(new Person("Anastasio", 96));
        person.add(new Person("Beatriz", 34));
        person.add(new Person("Carlos", 27));
        person.add(new Person("Diana", 45));
        person.add(new Person("Eduardo", 52));

        personRepository.saveAll(person);
        List<Person> allPerson = personRepository.findAll();
        for (Person people : allPerson) {
            System.out.println(people);
        }

        assertTrue(allPerson.get(0).getName() == "Anastasio");
        assertTrue(allPerson.get(0).getAge() == 96);

    }


    @Test
    @DisplayName("Delete")
    public void testDelete() {
        Person person = new Person("Abelardo", 7);
        personRepository.save(person);
        Optional<Person> person1 = personRepository.findById(person.getId());
        System.out.println(person1);
        assertNotNull(person1.get().getId());
        personRepository.delete(person);
        Optional<Person> personDeleted = personRepository.findById(person.getId());
        assertNull(person.getId());
    }

    @Test
    @DisplayName("Test Update")
    public void testUpdate() {
        Person person = new Person("Jose Ezequiel", 8);
        personRepository.save(person);
        Optional<Person> personFind = personRepository.findById(person.getId());
        if (personFind.isPresent()) {
            Person personUpdate = personFind.get();
            personUpdate.setName("Raul Alberto");
            personUpdate.setAge(5);
            personRepository.save(personUpdate);
            assertEquals("Raul Alberto",personUpdate);
            assertEquals(5,personUpdate);
        } else {
            System.out.println("person not found");
        }


    }

}
