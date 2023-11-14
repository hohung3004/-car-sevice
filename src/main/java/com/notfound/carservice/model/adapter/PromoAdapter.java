package com.notfound.carservice.model.adapter;


import com.notfound.carservice.domain.Promo;
import com.notfound.carservice.model.request.CreatePromoRequest;
import com.notfound.carservice.model.request.UpdatePromoRequest;
import com.notfound.carservice.model.response.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PromoAdapter {
    public Promo createPromo (CreatePromoRequest request) {
        return Promo.builder()
                .discountRate(request.getDiscountRate())
                .promotionCode(request.getPromotionCode())
                .statusPromotion(request.getStatusPromotion())
                .createAt(LocalDateTime.now())
                .build();
    }
    public Promo updatePromo(UpdatePromoRequest request){
        return Promo.builder()
                .id(request.getId())
                .discountRate(request.getDiscountRate())
                .promotionCode(request.getPromotionCode())
                .statusPromotion(request.getStatusPromotion())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public CreatePromoResponse buildCreatePromo(Promo p){
        return CreatePromoResponse.builder()
                .discountRate(p.getDiscountRate())
                .promotionCode(p.getPromotionCode())
                .statusPromotion(p.getStatusPromotion())
                .build();
    }

    public UpdatePromoResponse buildUpdatePromo(Promo p){
        return UpdatePromoResponse.builder()
                .discountRate(p.getDiscountRate())
                .promotionCode(p.getPromotionCode())
                .statusPromotion(p.getStatusPromotion())
                .build();
    }

    public GetPromoResponse buildGetAllPromoResponse(Promo promo) {
        return GetPromoResponse.builder()
                .id(promo.getId())
                .discountRate(promo.getDiscountRate())
                .promotionCode(promo.getPromotionCode())
                .statusPromotion(promo.getStatusPromotion())
                .build();
    }
}
