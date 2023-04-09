package com.firstspring.firstspring.Web.repository;

import com.firstspring.firstspring.Web.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


    @Repository
    public interface PhotoRepository extends CrudRepository<Photo, UUID> {

    }

