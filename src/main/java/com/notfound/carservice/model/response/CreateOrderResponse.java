package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateOrderResponse {


    private Integer idPromo;

    private Integer idUser;

    private LocalDateTime orderDate;

    private Float unitPrice;

    private String status;


}
