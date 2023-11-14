package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class CreateCartRequest {

    @NotNull( message = "Thành tiền không được để trống!")
    private Float unitPrice;

    private Integer quantity;

    @NotNull( message = "Tổng tiền thanh toán không được để trống!")
    private Float totalPayment;

    private String status;

    private LocalDateTime createAt;

    @NotNull( message = "Mã sản phẩm không được để trống!")
    private Integer idProduct;

    @NotNull( message = "Mã đơn hàng không được để trống!")
    private Integer idOrder;
}
