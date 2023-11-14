package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetOrderResponse {

    private Integer id;

    private Float unitPrice;

    private Integer userId;

    private Integer promoId;

    private String status;

    private LocalDateTime orderDate;

}
