package com.firstspring.firstspring.Web.mapper;

import com.firstspring.firstspring.Web.dto.PersonUpdateRequest;
import com.firstspring.firstspring.Web.model.Person;
import com.firstspring.firstspring.Web.dto.PersonCreateRequest;
import com.firstspring.firstspring.Web.dto.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(uses = AddressMapper.class)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    Person modelFromCreateRequest(PersonCreateRequest personCreateDto);

    PersonResponse responseFromModel(Person person);

    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "egnNumber", ignore = true)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "age", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "address", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(PersonUpdateRequest personUpdateDto, @MappingTarget Person person);
    List<PersonResponse> listOfModelToListOfDto(List<Person>persons);

    List<PersonResponse> listOfModelToListOfDto(Iterable<Person> all);
}


