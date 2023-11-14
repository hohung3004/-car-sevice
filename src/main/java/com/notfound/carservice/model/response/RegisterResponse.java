package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class RegisterResponse {
    private String fullName;

    private String email;

    private String role;

    private String status;
}
