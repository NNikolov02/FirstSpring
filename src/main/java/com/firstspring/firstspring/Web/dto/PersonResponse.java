package com.firstspring.firstspring.Web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PersonResponse {
    private UUID id;
    private String name;
    private Integer age;
    private AddressDto address;
    private String egnNumber;

}
