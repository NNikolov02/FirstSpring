package com.firstspring.firstspring.Web.repository;

import com.firstspring.firstspring.Web.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonPagingRepository extends PagingAndSortingRepository<Person, UUID> {

}
