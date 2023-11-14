package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.crypto.Mac;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateShowroomRequest {

    @NotNull(message = "Id không được để trống!")
    private Integer id;

    @NotBlank(message = "Tên showRoom không được để trống!")
    private String showName;

    @NotBlank(message = "Số điện thoại không được để trống!")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống!")
    private String address;

    @NotBlank(message = "Email không được để trống!")
    @Email(message = "Email không đúng định dạng!")
    private String email;

    private String status;

}
