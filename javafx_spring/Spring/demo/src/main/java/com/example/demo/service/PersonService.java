package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PersonService {

    private static final AtomicInteger PersonId = new AtomicInteger();

    @Autowired // автоматически создает данный объект
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public boolean delete(Person person) {
        if (person != null) {
            personRepository.delete(person);
            return true;
        }
        return false;
    }

    public Person update(Person person, Person personFromDB) {
        BeanUtils.copyProperties(person, personFromDB, "id");
        return personRepository.save(personFromDB);
    }

}
