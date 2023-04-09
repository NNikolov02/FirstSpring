package com.firstspring.firstspring.Web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;
@Data
@Builder

public class PersonPhotosGetResponse {

    private Set<UUID> personPhotosIds;



}
