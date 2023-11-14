package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateProductRequest;
import com.notfound.carservice.model.request.UpdateProductRequest;
import com.notfound.carservice.model.response.CreateProductResponse;
import com.notfound.carservice.model.response.GetProductResponse;
import com.notfound.carservice.model.response.UpdateProductResponse;

import java.util.List;

public interface ProductService {
    BaseResponseApi<CreateProductResponse> createProduct (CreateProductRequest request);
    BaseResponseApi<UpdateProductResponse> updateProduct (UpdateProductRequest request);
    BaseResponseApi<List<GetProductResponse>> getAllProduct();
    BaseResponseApi<GetProductResponse> findById(Integer id);
    BaseResponseApi<GetProductResponse> deleteById(Integer id);
}
