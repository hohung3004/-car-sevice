package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateWishListRequest;
import com.notfound.carservice.model.response.*;
import com.notfound.carservice.service.WishlistSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/wishlist")
@CrossOrigin("*")
public class WishListController {

    @Autowired
    private WishlistSevice wishlistSevice;

    @PostMapping("/create")
    public BaseResponseApi<CreateWishListResponse> create(@Valid @RequestBody CreateWishListRequest request) {
        BaseResponseApi<CreateWishListResponse> responseApi =  new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            responseApi = wishlistSevice.createWishList(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetWishListResponse>> getAllWishList(){
        BaseResponseApi<List<GetWishListResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = wishlistSevice.getAllWishList();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
    @DeleteMapping("/{id}")
    public BaseResponseApi<GetWishListResponse> deleteById(@PathVariable Integer id){
        BaseResponseApi<GetWishListResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = wishlistSevice.deleteById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }
}
