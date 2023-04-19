package com.firstspring.firstspring.Web;


import com.firstspring.firstspring.Web.dto.*;
import com.firstspring.firstspring.Web.error.InvalidObjectException;
import com.firstspring.firstspring.Web.mapper.PersonMapper;
import com.firstspring.firstspring.Web.model.Person;
import com.firstspring.firstspring.Web.service.PersonService;
import com.firstspring.firstspring.Web.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {






    private ObjectValidator validator;

    private PersonMapper personMapper;

    private PersonService personService;
    private final Integer Page_Size = 10;

    @GetMapping(name = "",produces = "application/json")
    public PersonApiPage<PersonResponse> getAllPersons(

        @RequestParam(required = false,defaultValue = "0") Integer currPage){
        Page<PersonResponse> personPage =
                personService.fetchAll(currPage, Page_Size).map(personMapper::responseFromModel);
        return new PersonApiPage<>(personPage);

//Sort.by("age").descending()
    }

    @GetMapping("/{personId}")
    public ResponseEntity <PersonResponse> getPersonById(@PathVariable String personId) {
        Person person = personService.findById(personId);

        return ResponseEntity.ok(personMapper.responseFromModel(person));
    }

    @DeleteMapping("/{personId}")
    public void deletePersonById(@PathVariable String personId) {
        personService.deleteById(personId);
    }

    @PostMapping("")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonCreateRequest personDto) {

        Map<String, String> validationErrors = validator.validate(personDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Create", validationErrors);
        }


        Person mappedPerson = personMapper.modelFromCreateRequest(personDto);

        Person savedPerson = personService.save(mappedPerson);

        PersonResponse responsePerson = personMapper.responseFromModel(savedPerson);

        return ResponseEntity.status(201).body(responsePerson);

    }
    @PatchMapping("/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable String personId, @RequestBody PersonUpdateRequest personDto){
        Map<String, String> validationErrors = validator.validate(personDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Create", validationErrors);
        }
        Person currentPerson = personService.findById(personId);

        personMapper.updateModelFromDto(personDto, currentPerson);

        Person updatedPerson = personService.save(currentPerson);

        PersonResponse responsePerson = personMapper.responseFromModel(updatedPerson);

        return ResponseEntity.status(200).body(responsePerson);


    }

    @GetMapping("/{personId}/photos")
    public PersonPhotosGetResponse getAllPersonPhotos(@PathVariable String personId) {

        Set<UUID> allPersonPhotoIds = personService.getAllPersonPhotoIds(personId);

        PersonPhotosGetResponse response =
                PersonPhotosGetResponse.builder().personPhotosIds(allPersonPhotoIds).build();

        return response;
    }

    @PutMapping(value = "/{personId}/photos")
    public PersonPhotosGetResponse setPersonPhotos(@PathVariable String personId,
                                                   @RequestBody SetPersonPhotosRequest request) {


        Map<String, String> validationErrors = validator.validate(request);

        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Photos Upsert Request",
                    validationErrors
            );
        }
        Set<UUID> allPersonPhotoIds = personService.setPersonPhotos(personId, request.getPersonPhotosIds());

        PersonPhotosGetResponse response =
                PersonPhotosGetResponse.builder().personPhotosIds(allPersonPhotoIds).build();

        return response;
    }

}



