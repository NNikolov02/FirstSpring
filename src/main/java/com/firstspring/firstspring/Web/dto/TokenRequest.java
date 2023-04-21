package com.firstspring.firstspring.Web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequest {
    private String Username;
    private String password;
}
