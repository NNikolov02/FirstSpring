package com.firstspring.firstspring.repository;

import com.firstspring.firstspring.model.Person;
import com.firstspring.firstspring.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


    @Repository
    public interface PhotoRepository extends CrudRepository<Photo, UUID> {

    }

