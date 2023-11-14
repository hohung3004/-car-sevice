package com.notfound.carservice.service;


import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreateCartRequest;
import com.notfound.carservice.model.request.UpdateCartRequest;
import com.notfound.carservice.model.response.CreateCartResponse;
import com.notfound.carservice.model.response.GetCartResponse;
import com.notfound.carservice.model.response.UpdateCartResponse;

import java.util.List;

public interface CartSevice {

    BaseResponseApi<CreateCartResponse> createCart (CreateCartRequest request);
    BaseResponseApi<UpdateCartResponse> updateCart (UpdateCartRequest request);
    BaseResponseApi<List<GetCartResponse>> getAllCart();
    BaseResponseApi<GetCartResponse> findById(Integer id);
    BaseResponseApi<GetCartResponse> deleteById(Integer id);
}
