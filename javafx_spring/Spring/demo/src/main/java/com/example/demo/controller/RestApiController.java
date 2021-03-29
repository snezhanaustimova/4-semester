package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {

    private final PersonService personService;

    @Autowired
    public RestApiController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/api/persons")
    private ResponseEntity<?> create(@RequestBody Person person) {
        Person newPerson = personService.create(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/persons")
    private ResponseEntity<List<Person>> readAll() {
        final List<Person> personList = personService.findAll();

        return personList != null && !personList.isEmpty()
                ? new ResponseEntity<>(personList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "api/persons/{id}")
    public ResponseEntity<Person> getOne(@PathVariable(name = "id") Person person) {
        final Person currentPerson = person;
        return person != null
                ? new ResponseEntity<>(currentPerson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "api/persons/{id}")
    public ResponseEntity<Person> put(@PathVariable(name = "id") Person personFromDB, @RequestBody Person person) {
        Person updatedPerson = personService.update(person, personFromDB);
        if (updatedPerson != null) {
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "api/persons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Person person) {
        if (personService.delete(person)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}