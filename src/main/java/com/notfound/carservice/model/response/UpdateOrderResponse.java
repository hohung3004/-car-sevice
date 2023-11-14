package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UpdateOrderResponse {

    private Integer id;

    private Float unitPrice;

    private String status;

    private Integer userId;

    private Integer promoId;

    private LocalDateTime updateAt;
}
