package com.firstspring.firstspring.Web;

import java.io.InvalidObjectException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.firstspring.firstspring.Web.dto.PersonCreateRequest;
import com.firstspring.firstspring.Web.dto.PersonPhotosGetResponse;
import com.firstspring.firstspring.Web.dto.PersonResponse;
import com.firstspring.firstspring.Web.dto.SetPersonPhotosRequest;
import com.firstspring.firstspring.model.Person;
import com.firstspring.firstspring.model.Photo;
import com.firstspring.firstspring.repository.PersonRepository;
import com.firstspring.firstspring.repository.PhotoRepository;
import com.firstspring.firstspring.error.NotFoundObjectException;
import com.firstspring.firstspring.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstspring.firstspring.validation.ObjectValidator;



@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @Autowired
    private PhotoRepository photoRepo;

    @Autowired
    private ObjectValidator validator;
    @Autowired
    private PersonMapper personMapper;

    @GetMapping("")
    public List<Person> getAllPersons() {
        return (List<Person>) repo.findAll();
    }

    @GetMapping("/{personId}")
    public Person getPersonById(@PathVariable String personId) {
        return repo.findById(UUID.fromString(personId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", Person.class.getName(), personId);
        });
    }

    @DeleteMapping("/{personId}")
    public void deletePersonById(@PathVariable String personId) {
        repo.deleteById(UUID.fromString(personId));
    }

    @PostMapping("")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonCreateRequest personDto) {

        Map<String, String> validationErrors = validator.validate(personDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Create", validationErrors);
        }


        Person mappedPerson = personMapper.modelFromCreateRequest(personDto);
        Person savedPerson = repo.save(mappedPerson);
        PersonResponse responsePerson = personMapper.responseFromModel(savedPerson);

        return ResponseEntity.status(201).body(responsePerson);

    }

    @GetMapping("/{personId}/photos")
    public PersonPhotosGetResponse getAllPersonPhotos(@PathVariable String personId) {

        Person person = repo.findById(UUID.fromString(personId)).get();

        Set<UUID> allPersonPhotoIds = new HashSet<>();
        for (Photo photo : person.getPhotos()) {
            allPersonPhotoIds.add(photo.getId());
        }

        PersonPhotosGetResponse response =
                PersonPhotosGetResponse.builder().personPhotosIds(allPersonPhotoIds).build();

        return response;
    }

    @PutMapping(value = "/{personId}/photos")
    public PersonPhotosGetResponse setPersonPhotos(@PathVariable String personId,
                                                   @RequestBody SetPersonPhotosRequest request) {
        Person person = repo.findById(UUID.fromString(personId)).get();

        Map<String, String> validationErrors = validator.validate(request);

        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Photos Upsert Request",
                    validationErrors
            );
        }

        List<Photo> allPersonPhotos =
                (List<Photo>) photoRepo.findAllById(request.getPersonPhotosIds());

        person.setPhotos(new HashSet<>(allPersonPhotos));
        Person savedPerson = repo.save(person);

        Set<UUID> allPersonPhotoIds = new HashSet<>();
        for (Photo photo : savedPerson.getPhotos()) {
            allPersonPhotoIds.add(photo.getId());
        }

        PersonPhotosGetResponse response =
                PersonPhotosGetResponse.builder().personPhotosIds(allPersonPhotoIds).build();

        return response;
    }

}



