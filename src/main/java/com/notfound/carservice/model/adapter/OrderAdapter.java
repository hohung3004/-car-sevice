package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.Order;
import com.notfound.carservice.domain.Promo;
import com.notfound.carservice.domain.User;
import com.notfound.carservice.model.request.CreateOrderRequest;
import com.notfound.carservice.model.request.UpdateOrderRequest;
import com.notfound.carservice.model.response.CreateOrderResponse;
import com.notfound.carservice.model.response.GetOrderResponse;
import com.notfound.carservice.model.response.UpdateOrderResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderAdapter {

    public Order createOrder (CreateOrderRequest request){
        return Order.builder()
                .promo(new Promo(request.getIdPromo()))
                .user(new User(request.getIdUser()))
                .orderDate(LocalDateTime.now())
                .unitPrice(request.getUnitPrice())
                .status("1")
                .build();
    }

    public CreateOrderResponse buildCreateOrder (Order order){
        return CreateOrderResponse.builder()
                .idPromo(order.getPromo().getId())
                .idUser(order.getUser().getId())
                .orderDate(order.getOrderDate())
                .unitPrice(order.getUnitPrice())
                .status(order.getStatus())
                .build();
    }

    public Order updateOrder (UpdateOrderRequest request){
        return Order.builder()
                .id(request.getId())
                .promo(new Promo(request.getPromoId()))
                .user(new User(request.getUserId()))
                .unitPrice(request.getUnitPrice())
                .updateAt(LocalDateTime.now())
                .status(request.getStatus())
                .build();
    }

    public UpdateOrderResponse buildUpdateOrder (Order o){
        return UpdateOrderResponse.builder()
                .id(o.getId())
                .promoId(o.getPromo().getId())
                .userId(o.getUser().getId())
                .unitPrice(o.getUnitPrice())
                .status(o.getStatus())
                .updateAt(o.getUpdateAt())
                .build();
    }

    public GetOrderResponse getAllOrder (Order order){
        return GetOrderResponse.builder()
                .id(order.getId())
                .promoId(order.getPromo().getId())
                .userId(order.getUser().getId())
                .unitPrice(order.getUnitPrice())
                .status(order.getStatus())
                .orderDate(LocalDateTime.now())
                .build();
    }
}
