package com.firstspring.firstspring.Web.repository;

import com.firstspring.firstspring.Web.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;



@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {
    List<Person> findDistinctPeopleByNameOrEgnNumber(String name, String egnNumber);

    List<Person> findByNameIgnoreCase(String name);

    List<Person> findByNameAndEgnNumberAllIgnoreCase(String name, String egnNumber);

    List<Person> findByNameOrderByNameAsc(String name);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN\n" +
            "            TRUE ELSE FALSE END\n" +
            "            FROM Person p\n" +
            "            WHERE p.egnNumber = ?1")

    boolean existByEgnNumber(String egnNumber);

}


