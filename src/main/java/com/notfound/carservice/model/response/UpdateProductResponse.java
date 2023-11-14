package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductResponse {

    private String productName;

    private double productPrice;

    private float discountRate;

    private Integer seatCount;

    private String gearBox;

    private String rearTorage;

    private String fuel;

    private Integer doorQuantity;

    private String color;

    private String description;

    private String status;
}
