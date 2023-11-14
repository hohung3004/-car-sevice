package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateShowRoomResponse {

    private String showName;

    private String address;

    private String phone;

    private String email;

    private String status;
}
