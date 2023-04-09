package com.firstspring.firstspring.mapper;

import com.firstspring.firstspring.model.Address;
import com.firstspring.firstspring.Web.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {



    AddressDto modelRoDto(Address address);

    Address dtoModel(AddressDto addressDto);



}
