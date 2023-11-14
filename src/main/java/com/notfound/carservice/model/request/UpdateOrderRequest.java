package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@Builder
public class UpdateOrderRequest {

    @NotNull( message = "Id không được để trống!")
    private Integer id;

    @NotNull( message = "Thành tiền không được để trống!")
    private Float unitPrice;

    private LocalDateTime updateAt;

    @NotBlank( message = "Status không được để trống!")
    private String status;

    private Integer userId;

    private Integer promoId;
}
