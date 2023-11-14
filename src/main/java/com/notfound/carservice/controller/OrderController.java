package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateOrderRequest;
import com.notfound.carservice.model.request.UpdateOrderRequest;
import com.notfound.carservice.model.request.UpdateProductRequest;
import com.notfound.carservice.model.response.*;
import com.notfound.carservice.service.OrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderSevice orderSevice;

    @PostMapping("/create")
    public BaseResponseApi<CreateOrderResponse> create(@Valid @RequestBody CreateOrderRequest request){
        BaseResponseApi<CreateOrderResponse> responseApi =  new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            responseApi = orderSevice.createOrder(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }

    @PostMapping("/update")
    public BaseResponseApi<UpdateOrderResponse> update(@Valid @RequestBody UpdateOrderRequest request){
        BaseResponseApi<UpdateOrderResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = orderSevice.updateOrder(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetOrderResponse>> getAllOrder(){
        BaseResponseApi<List<GetOrderResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = orderSevice.getAllOrder();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/{id}")
    public BaseResponseApi<GetOrderResponse> findOrderById(@PathVariable Integer id){
        BaseResponseApi<GetOrderResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = orderSevice.findById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @DeleteMapping("/{id}")
    public BaseResponseApi<GetOrderResponse> deleteById(@PathVariable Integer id){
        BaseResponseApi<GetOrderResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = orderSevice.deleteById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }
}
