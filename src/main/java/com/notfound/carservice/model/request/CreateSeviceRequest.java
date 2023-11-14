package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateSeviceRequest {
    @NotBlank(message = "Icon không được để trống!")
    private String icon;

    @NotBlank(message = "Title không được để trống!")
    private String title;

    @NotBlank(message = "Summary không được để trống!")
    private String summary;

}
