package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.*;
import com.notfound.carservice.model.request.CreateProductRequest;
import com.notfound.carservice.model.request.UpdateProductRequest;
import com.notfound.carservice.model.response.CreateProductResponse;
import com.notfound.carservice.model.response.GetProductResponse;
import com.notfound.carservice.model.response.UpdateProductResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProductAdapter {

    public Product createProduct(CreateProductRequest request){
        Set<Image> setImage = new HashSet<>();
        if (request.getImages() != null) {
            String[] images = request.getImages().split(":");
            for (String image:
                 images) {
                Image img = Image.builder().image(image).createAt(LocalDateTime.now()).build();
                setImage.add(img);
            }
        }

        Set<Showroom> setShowroom = new HashSet<>();
        if (request.getShowrooms() != null) {
            String[] showrooms = request.getShowrooms().split(":");
            for (String showroom:
                    showrooms) {
                Showroom sh = Showroom.builder().id(Integer.getInteger(showroom)).build();
                setShowroom.add(sh);
            }
        }

        return Product.builder()
                .brand(new Brand(request.getIdBrand()))
                .image(setImage)
                .showroom(setShowroom)
                .productName(request.getProductName())
                .meetingDay(LocalDateTime.now())
                .createAt(LocalDateTime.now())
                .productPrice(request.getProductPrice())
                .discountRate(request.getDiscountRate())
                .status("1")
                .build();
    }

    public Product UpdateProduct (UpdateProductRequest request){
        return Product.builder()
                .id(request.getId())
                .productName(request.getProductName())
                .productPrice(request.getProductPrice())
                .discountRate(request.getDiscountRate())
                .status(request.getStatus())
                .build();
    }

    public CreateProductResponse buildCreateProduct(Product p, ProductDetail productDetail){
        return CreateProductResponse.builder()
                .brandId(p.getBrand().getId())
                .productName(p.getProductName())
                .meetingDay(LocalDateTime.now())
                .productPrice(p.getProductPrice())
                .discountRate(p.getDiscountRate())
                .seatCount(productDetail.getSeatCount())
                .gearBox(productDetail.getGearBox())
                .rearTorage(productDetail.getRearTorage())
                .fuel(productDetail.getFuel())
                .doorQuantity(productDetail.getDoorQuantity())
                .color(productDetail.getColor())
                .description(productDetail.getDescription())
                .status(p.getStatus())
                .build();
    }

    public UpdateProductResponse buildUpdateProduct(Product p){
        return UpdateProductResponse.builder()
                .productName(p.getProductName())
                .productPrice(p.getProductPrice())
                .discountRate(p.getDiscountRate())
                .status(p.getStatus())
                .build();
    }

    public GetProductResponse buildGetAllProduct(Product p){
        return GetProductResponse.builder()
                .id(p.getId())
                .productName(p.getProductName())
                .meetingDay(LocalDateTime.now())
                .productPrice(p.getProductPrice())
                .seatCount(p.getProductDetail().getSeatCount())
                .gearBox(p.getProductDetail().getGearBox())
                .rearTorage(p.getProductDetail().getRearTorage())
                .fuel(p.getProductDetail().getFuel())
                .doorQuantity(p.getProductDetail().getDoorQuantity())
                .color(p.getProductDetail().getColor())
                .description(p.getProductDetail().getDescription())
                .status(p.getStatus())
                .build();
    }

}
