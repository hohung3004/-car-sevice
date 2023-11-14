package com.notfound.carservice.model.response;

import com.notfound.carservice.domain.Brand;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateProductResponse {

    private Integer brandId;

    private String productName;

    private LocalDateTime meetingDay;

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
