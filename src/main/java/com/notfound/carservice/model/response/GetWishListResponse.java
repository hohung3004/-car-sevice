package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetWishListResponse {

    private Integer id;

    private Integer idUser;

    private Integer idProduct;

    private String status;
}
