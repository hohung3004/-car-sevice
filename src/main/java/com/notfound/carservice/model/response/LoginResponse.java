package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginResponse {

    private String fullName;

    private String email;

    private String status;

    private String role;

}
