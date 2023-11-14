package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPromoResponse {
    private  Integer id;

    private Float discountRate;

    private String statusPromotion;

    private String promotionCode;


}
