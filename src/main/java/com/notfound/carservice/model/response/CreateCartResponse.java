package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateCartResponse {

    private Integer id;

    private Integer idProduct;

    private Integer idOrder;

    private Integer quantity;

    private Float unitPrice;

    private Float totalPayment;

    private String status;

    private LocalDateTime createAt;
}
