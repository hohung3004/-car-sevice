package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateSeviceRequest {

    @NotNull(message = "Id không được để trống")
    private Integer id;

    @NotBlank(message = "Icon không được để trống!")
    private String icon;

    @NotBlank(message = "Title không được để trống!")
    private String title;

    @NotBlank(message = "Summary không được để trống!")
    private String summary;

    @NotBlank(message = "Status không được để trống!")
    private String status;
}
