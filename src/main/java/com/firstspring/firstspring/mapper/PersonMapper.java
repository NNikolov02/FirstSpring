package com.firstspring.firstspring.mapper;

import com.firstspring.firstspring.model.Person;
import com.firstspring.firstspring.Web.dto.PersonCreateRequest;
import com.firstspring.firstspring.Web.dto.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = AddressMapper.class)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    Person modelFromCreateRequest(PersonCreateRequest personCreateDto);

    PersonResponse responseFromModel(Person person);
}


