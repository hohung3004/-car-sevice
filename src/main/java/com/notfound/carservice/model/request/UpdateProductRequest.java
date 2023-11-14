package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder

public class UpdateProductRequest {

    @NotNull(message = "Id không đươc để trống!")
    private Integer id;

    @NotBlank(message = "Không được để trống tên sản phẩm!")
    private String productName;

    @NotNull(message = "Không được để trống giá sản phẩm!")
    private double productPrice;

    @Range(min = 0, max = 100, message = "Giá trị discount rate không hợp lệ!")
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
