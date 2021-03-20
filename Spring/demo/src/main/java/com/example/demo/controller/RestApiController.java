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
    public RestApiController (PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/api/person")
    private ResponseEntity<?> create(@RequestBody Person person) {
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/persons")
    private  ResponseEntity<List<Person>> readAll() {
        final List<Person> personList = personService.findAll();

        return personList != null && !personList.isEmpty()
                ? new ResponseEntity<>(personList, HttpStatus.OK)
                : new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "api/persons/{id}")
    public ResponseEntity<Person> read(@PathVariable(name = "id") int id) {
        final Person person = personService.read(id);
        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "api/persons/{id}")
    public ResponseEntity<Person> put(@PathVariable(name = "id") int id, @RequestBody Person newPerson) {
        if (personService.update(newPerson, id)) {
            Person newPersonWithId = personService.read(id);
            return new ResponseEntity<>(newPersonWithId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "api/persons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final Person person = personService.read(id);
        if (person != null) {
            personService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}