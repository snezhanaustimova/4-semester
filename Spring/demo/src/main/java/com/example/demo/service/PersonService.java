package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
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
        final int finalPersonId = PersonId.incrementAndGet();
        person.setId((long) finalPersonId);
        personRepository.save(person);
        return person;
    }

    public Person read(int id) {
        return personRepository.findById(id);
    }

    public boolean delete(int id) {
        Person currPerson = personRepository.findById(id);
        if (currPerson != null) {
            personRepository.delete(currPerson);
            return true;
        }
        return false;
    }

    public boolean update(Person person, int id) {
        Person currPerson = personRepository.findById(id);
        if (currPerson == null) {
            return false;
        }
        // удаление и вставка новых данных
        personRepository.delete(currPerson);
        personRepository.save(person);
        return true;
    }

}
