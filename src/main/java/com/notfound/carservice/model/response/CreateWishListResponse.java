package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class CreateWishListResponse {

    private Integer id;

    private String status;

    private LocalDateTime createAt;

    private Integer idUser;

    private Integer idProduct;
}
