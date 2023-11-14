package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateBrandRequest {

    @NotBlank(message = "Tên không được để trống!")
    private String brandName;

    @NotBlank(message = "Logo không được để trống!")
    private String logo;
}
