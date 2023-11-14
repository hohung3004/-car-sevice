package com.notfound.carservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest implements Serializable {

    private Integer id;

    private Integer idPromo;

    private Integer idUser;

    private LocalDateTime orderDate;

    @NotNull( message = "Thành tiền không được để trống!")
    private Float unitPrice;

    private String status;


}
