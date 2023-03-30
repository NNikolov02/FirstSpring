package com.firstspring.firstspring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.firstspring.firstspring.repository.PersonRepository;
import com.firstspring.firstspring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/persons")
public class SimpleController {

@Autowired
private PersonRepository repo;
    @GetMapping("/{id}")
    public List<Person> addPerson() {
        return (List<Person>)repo.findAll();



    }
    @GetMapping("/{personid}")
    public Person getPersonbyid(@PathVariable String personid) {
        return  repo.findById(UUID.fromString(personid)).get();



    }
    @GetMapping("/{personid}")
    public void deletePersonbyid(@PathVariable String personid) {
        repo.deleteById(UUID.fromString(personid));



    }

    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person newPerson = repo.save(person);
       return  ResponseEntity.status(201).body(newPerson);
    }
}



