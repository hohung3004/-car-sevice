package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreatePromoRequest {
    @NotNull(message = "discountRate không được để trống!")
    private Float discountRate;

    @NotBlank(message = "statusPromotion không được để trống!")
    private String statusPromotion;

    @NotBlank(message = "statusPromotion không được để trống!")
    private String promotionCode;
}
