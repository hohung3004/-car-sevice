package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreateOrderRequest;
import com.notfound.carservice.model.request.UpdateOrderRequest;
import com.notfound.carservice.model.response.CreateOrderResponse;
import com.notfound.carservice.model.response.GetOrderResponse;
import com.notfound.carservice.model.response.UpdateOrderResponse;

import java.util.List;

public interface OrderSevice {
    BaseResponseApi<CreateOrderResponse> createOrder (CreateOrderRequest request);
    BaseResponseApi<UpdateOrderResponse> updateOrder (UpdateOrderRequest request);
    BaseResponseApi<List<GetOrderResponse>> getAllOrder();
    BaseResponseApi<GetOrderResponse> findById(Integer id);
    BaseResponseApi<GetOrderResponse> deleteById(Integer id);
}
