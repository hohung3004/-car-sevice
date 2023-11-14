package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class UpdateCartRequest {

    @NotNull( message = "Id không được để trống!")
    private Integer id;

    private Integer idOrder;

    private Integer idProduct;

    @NotNull( message = "Số lượng không được để trống!")
    private Integer quantity;

    private Float totalPayment;

    private Float unitPrice;

    @NotBlank( message = "Status không được để trống!")
    private String status;

    private LocalDateTime updateAt;


}
