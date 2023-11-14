package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.Cart;
import com.notfound.carservice.domain.Order;
import com.notfound.carservice.domain.Product;
import com.notfound.carservice.model.request.CreateCartRequest;
import com.notfound.carservice.model.request.UpdateCartRequest;
import com.notfound.carservice.model.response.CreateCartResponse;
import com.notfound.carservice.model.response.GetCartResponse;
import com.notfound.carservice.model.response.UpdateCartResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartAdapter {

    public Cart createCart (CreateCartRequest request){
        return Cart.builder()
                .order(new Order(request.getIdOrder()))
                .product(new Product(request.getIdProduct()))
                .unitPrice(request.getUnitPrice())
                .quantity(1)
                .createAt(LocalDateTime.now())
                .totalPayment(request.getTotalPayment())
                .status("1")
                .build();
    }

    public CreateCartResponse buildCreateCart( Cart c){
        return CreateCartResponse.builder()
                .id(c.getId())
                .createAt(c.getCreateAt())
                .idOrder(c.getOrder().getId())
                .idProduct(c.getProduct().getId())
                .quantity(c.getQuantity())
                .unitPrice(c.getUnitPrice())
                .totalPayment(c.getTotalPayment())
                .status(c.getStatus())
                .build();
    }

    public Cart updateCart (UpdateCartRequest request){
        return Cart.builder()
                .id(request.getId())
                .order(new Order(request.getIdOrder()))
                .product(new Product(request.getIdProduct()))
                .totalPayment(request.getTotalPayment())
                .unitPrice(request.getUnitPrice())
                .quantity(request.getQuantity())
                .updateAt(LocalDateTime.now())
                .status(request.getStatus())
                .build();
    }

    public UpdateCartResponse buildUpdateCart (Cart c){
        return UpdateCartResponse.builder()
                .id(c.getId())
                .idOrder(c.getOrder().getId())
                .idProduct(c.getProduct().getId())
                .unitPrice(c.getUnitPrice())
                .quantity(c.getQuantity())
                .updateAt(c.getUpdateAt())
                .totalPayment(c.getTotalPayment())
                .status(c.getStatus())
                .build();
    }

    public GetCartResponse getAllCart (Cart cart){
        return GetCartResponse.builder()
                .id(cart.getId())
                .idOrder(cart.getOrder().getId())
                .idProduct(cart.getProduct().getId())
                .quantity(cart.getQuantity())
                .status(cart.getStatus())
                .totalPayment(cart.getTotalPayment())
                .unitPrice(cart.getUnitPrice())
                .build();
    }
}
