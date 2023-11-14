package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateBrandRequest {

    @NotNull(message = "Không được để trống id!")
    private Integer id;

    @NotBlank(message = "Thương hiệu không được để trống!")
    private String brandName;

    @NotBlank(message = "Logo không được để trống!")
    private String logo;

    private String status;
}
