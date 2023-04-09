package com.firstspring.firstspring.Web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private String street;
    private int number;
}
