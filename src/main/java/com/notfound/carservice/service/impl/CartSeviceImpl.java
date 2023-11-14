package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Cart;
import com.notfound.carservice.model.adapter.CartAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateCartRequest;
import com.notfound.carservice.model.request.UpdateCartRequest;
import com.notfound.carservice.model.response.*;
import com.notfound.carservice.repository.CartRepository;
import com.notfound.carservice.service.CartSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CartSeviceImpl implements CartSevice {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartAdapter cartAdapter;

    @Override
    public BaseResponseApi<CreateCartResponse> createCart(CreateCartRequest request) {
        BaseResponseApi<CreateCartResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Cart foundCart = cartRepository.findCartByCreate(request.getIdOrder(), request.getIdProduct());
            if (foundCart != null) {
                foundCart.setQuantity(foundCart.getQuantity() + 1);
                foundCart.setTotalPayment(foundCart.getQuantity() * foundCart.getUnitPrice());
                cartRepository.save(foundCart);
                responseStatus.setSuccess();
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Cart cart = cartRepository.save(cartAdapter.createCart(request));
            if (cart == null) {
                responseStatus.setFailed("Thêm sản phẩm vào giỏ hàng không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(cartAdapter.buildCreateCart(cart));
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
    public BaseResponseApi<UpdateCartResponse> updateCart(UpdateCartRequest request) {
        BaseResponseApi<UpdateCartResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Cart foundCart = cartRepository.findByCartByUpdate(request.getId());
            if (foundCart != null) {
                responseStatus.setFailed("Giỏ hàng không tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Cart cart = cartRepository.save(cartAdapter.updateCart(request));

            if (cart == null) {
                responseStatus.setFailed("Cập nhật không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(cartAdapter.buildUpdateCart(cart));
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
    public BaseResponseApi<List<GetCartResponse>> getAllCart() {
        BaseResponseApi<List<GetCartResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetCartResponse> allCart = new ArrayList<>();

        try {
            List<Cart> cartList = cartRepository.findAll();

            for (Cart cart :
                    cartList) {
                if (cart.getDeleteAt() == null) {
                    allCart.add(cartAdapter.getAllCart(cart));
                }
            }

            responseApi.setData(allCart);
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
    public BaseResponseApi<GetCartResponse> findById(Integer id) {
        BaseResponseApi<GetCartResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Optional<Cart> cart = cartRepository.findById(id);
            if (cart.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy giỏ hàng có sản phẩm với id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseStatus.setSuccess();
            responseApi.setData(cartAdapter.getAllCart(cart.get()));
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
    public BaseResponseApi<GetCartResponse> deleteById(Integer id) {
        BaseResponseApi<GetCartResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Cart> cart = cartRepository.findById(id);
            if (cart.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy giỏ hàng có sản phẩm với id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            cart.get().setDeleteAt(LocalDateTime.now());

            Cart deleteCart = cartRepository.save(cart.get());

            if (deleteCart == null){
                responseStatus.setFailed("Xoá sản phẩm trong không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(cartAdapter.getAllCart(deleteCart));
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
