package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.Product;
import com.notfound.carservice.domain.ProductDetail;
import com.notfound.carservice.model.request.CreateProductRequest;
import com.notfound.carservice.model.response.CreateProductResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductDetailAdapter {

    public ProductDetail createProductDetail(CreateProductRequest request, Product product){
        return ProductDetail.builder()
                .product(product)
                .seatCount(request.getSeatCount())
                .gearBox(request.getGearBox())
                .rearTorage(request.getRearTorage())
                .fuel(request.getFuel())
                .doorQuantity(request.getDoorQuantity())
                .color(request.getColor())
                .description(request.getDescription())
                .createAt(LocalDateTime.now())
                .status("1")
                .build();
    }

    public CreateProductResponse buildCreateProductDetail(ProductDetail productDetail){
        return CreateProductResponse.builder()
                .seatCount(productDetail.getSeatCount())
                .gearBox(productDetail.getGearBox())
                .rearTorage(productDetail.getRearTorage())
                .fuel(productDetail.getFuel())
                .doorQuantity(productDetail.getDoorQuantity())
                .color(productDetail.getColor())
                .description(productDetail.getDescription())
                .status(productDetail.getStatus())
                .build();
    }
}
