package com.firstspring.firstspring.Web;



import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.firstspring.firstspring.Web.dto.*;
import com.firstspring.firstspring.Web.error.InvalidObjectException;
import com.firstspring.firstspring.Web.model.Person;
import com.firstspring.firstspring.Web.model.Photo;
import com.firstspring.firstspring.Web.repository.PersonPagingRepository;
import com.firstspring.firstspring.Web.repository.PersonRepository;
import com.firstspring.firstspring.Web.repository.PhotoRepository;
import com.firstspring.firstspring.Web.error.NotFoundObjectException;
import com.firstspring.firstspring.Web.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.firstspring.firstspring.Web.validation.ObjectValidator;
import org.springframework.web.service.annotation.PatchExchange;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repo;
    @Autowired
    private PersonPagingRepository pagingRepo;

    @Autowired
    private PhotoRepository photoRepo;

    @Autowired
    private ObjectValidator validator;
    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private PersonMapper personMapper;
    private final Integer Page_Size = 10;

    @GetMapping("")
    public Page<PersonResponse> getAllPersons(@RequestParam Integer currPage) {

        return  pagingRepo.findAll(PageRequest.of(currPage,Page_Size)).map(personMapper::responseFromModel);

    }

    @GetMapping("/{personId}")
    public ResponseEntity <PersonResponse> getPersonById(@PathVariable String personId) {
        Person person = repo.findById(UUID.fromString(personId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", Person.class.getName(), personId);
        });
        return ResponseEntity.ok(personMapper.responseFromModel(person));
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
    @PatchMapping("/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable String personId, @RequestBody PersonUpdateRequest personDto){
        Map<String, String> validationErrors = validator.validate(personDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Create", validationErrors);
        }
        Person currentPerson = repo.findById(UUID.fromString(personId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", Person.class.getName(), personId);

    });
        personMapper.updateModelFromDto(personDto,currentPerson);

        Person updatePerson = repo.save(currentPerson);
        PersonResponse personResponse = personMapper.responseFromModel(updatePerson);

        return ResponseEntity.status(200).body(personResponse);


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



