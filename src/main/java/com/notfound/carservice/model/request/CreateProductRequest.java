package com.notfound.carservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest implements Serializable {

    private Integer idBrand;

    @NotBlank(message = "Không được để trống tên sản phẩm!")
    private String productName;

    private LocalDateTime meetingDay;

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

    // save list link image - format = url1:url2:url3
    private String images;

    // save list id - format = url1:url2:url3
    private String showrooms;

}

