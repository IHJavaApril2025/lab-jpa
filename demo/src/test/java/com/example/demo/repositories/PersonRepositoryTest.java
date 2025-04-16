package com.example.demo.repositories;

import com.example.demo.models.Person;
import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
public class PersonRepositoryTest {

    private final PersonRepository personRepository;

    // Inyección por constructor (más segura que @Autowired en el campo)
    @Autowired
    public PersonRepositoryTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    public void testSave() {
        // Crear una instancia de Person
        Person person = new Person("John Doe", 30);

        // Guardar en el repositorio
        Person savedPerson = personRepository.save(person);

        // Buscar por ID
        Optional<Person> optionalPerson = personRepository.findById(savedPerson.getId());

        // Comprobar que se encontró la persona
        assertTrue(optionalPerson.isPresent());

        // Obtener y mostrar la persona
        Person foundPerson = optionalPerson.get();
        System.out.println(foundPerson);

        // Comprobar que el ID no es nulo
        assertNotNull(foundPerson.getId());

        // Comprobar nombre y edad
        assertEquals("John Doe", foundPerson.getName());
        assertEquals(30, foundPerson.getAge());
    }
    @Test
    public void testFindAll() {
        // Crear una lista de personas
        List<Person> people = new ArrayList<>();
        people.add(new Person("Ditto", 30));
        people.add(new Person("Ash", 25));
        people.add(new Person("Misty", 22));

        // Guardar todas las personas en el repositorio
        personRepository.saveAll(people);

        // Buscar todas las personas en el repositorio
        List<Person> allPeople = personRepository.findAll();

        // Comprobar que no es nulo
        assertNotNull(allPeople);

        // Imprimir la lista por consola
        System.out.println("Personas encontradas:");
        allPeople.forEach(System.out::println);

        // Comprobar nombre y edad de la primera persona guardada
        Person ditto = allPeople.stream()
                .filter(p -> p.getName().equals("Ditto"))
                .findFirst()
                .orElseThrow();

        assertEquals("Ditto", ditto.getName());
        assertEquals(30, ditto.getAge());
    }
    @Test
    public void testDelete() {
        // Crear una persona
        Person person = new Person("Brock", 35);

        // Guardar en el repositorio
        Person savedPerson = personRepository.save(person);

        // Verificar que fue guardada correctamente
        Optional<Person> optionalPersonBeforeDelete = personRepository.findById(savedPerson.getId());
        assertTrue(optionalPersonBeforeDelete.isPresent());

        // Eliminar la persona
        personRepository.delete(savedPerson);

        // Buscar de nuevo después del delete
        Optional<Person> optionalPersonAfterDelete = personRepository.findById(savedPerson.getId());
        assertFalse(optionalPersonAfterDelete.isPresent());

        // Imprimir por consola
        System.out.println("Persona eliminada: " + savedPerson);

        // Comprobar que el ID de la persona ya no está en la base de datos
        assertTrue(optionalPersonAfterDelete.isEmpty());
    }
    @Test
    public void testUpdate() {
        // Crear una persona
        Person person = new Person("Gary", 28);

        // Guardar en el repositorio
        Person savedPerson = personRepository.save(person);

        // Buscar y comprobar que existe
        Optional<Person> optionalPerson = personRepository.findById(savedPerson.getId());
        assertTrue(optionalPerson.isPresent());

        // Obtener la persona y modificar sus datos
        Person personToUpdate = optionalPerson.get();
        personToUpdate.setName("Nuevo nombre");
        personToUpdate.setAge(30);

        // Guardar los cambios
        personRepository.save(personToUpdate);

        // Volver a buscar para verificar actualización
        Optional<Person> updatedOptionalPerson = personRepository.findById(personToUpdate.getId());
        assertTrue(updatedOptionalPerson.isPresent());

        // Obtener la persona actualizada
        Person updatedPerson = updatedOptionalPerson.get();

        // Imprimir por consola
        System.out.println("Persona actualizada: " + updatedPerson);

        // Comprobar que el ID no es nulo
        assertNotNull(updatedPerson.getId());

        // Comprobar que los datos se actualizaron correctamente
        assertEquals("Nuevo nombre", updatedPerson.getName());
        assertEquals(30, updatedPerson.getAge());
    }

}