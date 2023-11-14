package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartResponse {

    private Integer id;

    private Integer idProduct;

    private Integer idOrder;

    private Float unitPrice;

    private Float totalPayment;

    private String status;

    private Integer quantity;
}
