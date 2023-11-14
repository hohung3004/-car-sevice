package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.Product;
import com.notfound.carservice.domain.User;
import com.notfound.carservice.domain.Wishlist;
import com.notfound.carservice.model.request.CreateWishListRequest;
import com.notfound.carservice.model.response.CreateWishListResponse;
import com.notfound.carservice.model.response.GetWishListResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WishListAdapter {

    public Wishlist createWishList (CreateWishListRequest request){
        return Wishlist.builder()
                .product(new Product(request.getIdProduct()))
                .user(new User(request.getIdUser()))
                .status("1")
                .createAt(LocalDateTime.now())
                .build();
    }

    public CreateWishListResponse buildCreateWishList(Wishlist w){
        return CreateWishListResponse.builder()
                .id(w.getId())
                .idProduct(w.getProduct().getId())
                .idUser(w.getUser().getId())
                .createAt(w.getCreateAt())
                .status(w.getStatus())
                .build();
    }

    public GetWishListResponse buildGetAllWishList (Wishlist w){
        return GetWishListResponse.builder()
                .id(w.getId())
                .idProduct(w.getProduct().getId())
                .idUser(w.getUser().getId())
                .status(w.getStatus())
                .build();
    }

}
