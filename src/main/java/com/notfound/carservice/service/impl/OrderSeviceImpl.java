package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Order;
import com.notfound.carservice.model.adapter.OrderAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateOrderRequest;
import com.notfound.carservice.model.request.UpdateOrderRequest;
import com.notfound.carservice.model.response.*;
import com.notfound.carservice.repository.OrderReponsitory;
import com.notfound.carservice.service.OrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderSeviceImpl implements OrderSevice {

    @Autowired
    private OrderReponsitory orderReponsitory;

    @Autowired
    private OrderAdapter orderAdapter;

    @Override
    public BaseResponseApi<CreateOrderResponse> createOrder(CreateOrderRequest request) {
        BaseResponseApi<CreateOrderResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Order foundOrder = orderReponsitory.findOrderByCreate(request.getIdUser(), request.getIdPromo());
            if (foundOrder != null) {
                responseStatus.setFailed("Đơn hàng đã tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Order order = orderReponsitory.save(orderAdapter.createOrder(request));
            if (order == null) {
                responseStatus.setFailed("Thêm đơn hàng không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(orderAdapter.buildCreateOrder(order));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
            return responseApi;
        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<UpdateOrderResponse> updateOrder(UpdateOrderRequest request) {
        BaseResponseApi<UpdateOrderResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Order foundOrder = orderReponsitory.findByOrderByUpdate(request.getId());
            if (foundOrder != null) {
                responseStatus.setFailed("Đơn hàng không tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Order order = orderReponsitory.save(orderAdapter.updateOrder(request));

            if (order == null) {
                responseStatus.setFailed("Cập nhật không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(orderAdapter.buildUpdateOrder(order));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
            return responseApi;

        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<List<GetOrderResponse>> getAllOrder() {
        BaseResponseApi<List<GetOrderResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetOrderResponse> allOrder = new ArrayList<>();

        try {
            List<Order> orderList = orderReponsitory.findAll();

            for (Order order :
                    orderList) {
                if (order.getDeleteAt() == null) {
                    allOrder.add(orderAdapter.getAllOrder(order));
                }
            }

            responseApi.setData(allOrder);
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
            return responseApi;

        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<GetOrderResponse> findById(Integer id) {
        BaseResponseApi<GetOrderResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Optional<Order> order = orderReponsitory.findById(id);
            if (order.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy đơn hàng có id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseStatus.setSuccess();
            responseApi.setData(orderAdapter.getAllOrder(order.get()));
            responseApi.setResponseStatus(responseStatus);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<GetOrderResponse> deleteById(Integer id) {
        BaseResponseApi<GetOrderResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Order> order = orderReponsitory.findById(id);
            if (order.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy đơn hàng có id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            order.get().setDeleteAt(LocalDateTime.now());

            Order deleteOrder = orderReponsitory.save(order.get());

            if (deleteOrder == null){
                responseStatus.setFailed("Xoá đơn hàng không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(orderAdapter.getAllOrder(deleteOrder));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
}
