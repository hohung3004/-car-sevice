package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateCartRequest;
import com.notfound.carservice.model.request.UpdateCartRequest;
import com.notfound.carservice.model.response.*;
import com.notfound.carservice.service.CartSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/api/cart")
public class CartController {

    @Autowired
    private CartSevice cartSevice;

    @PostMapping("/create")
    public BaseResponseApi<CreateCartResponse> create(@Valid @RequestBody CreateCartRequest request){
        BaseResponseApi<CreateCartResponse> responseApi =  new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            responseApi = cartSevice.createCart(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }

    @PostMapping("/update")
    public BaseResponseApi<UpdateCartResponse> update(@Valid @RequestBody UpdateCartRequest request){
        BaseResponseApi<UpdateCartResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = cartSevice.updateCart(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetCartResponse>> getAllCart(){
        BaseResponseApi<List<GetCartResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = cartSevice.getAllCart();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/{id}")
    public BaseResponseApi<GetCartResponse> findCartById(@PathVariable Integer id){
        BaseResponseApi<GetCartResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = cartSevice.findById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @DeleteMapping("/{id}")
    public BaseResponseApi<GetCartResponse> deleteById(@PathVariable Integer id){
        BaseResponseApi<GetCartResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = cartSevice.deleteById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }
}
