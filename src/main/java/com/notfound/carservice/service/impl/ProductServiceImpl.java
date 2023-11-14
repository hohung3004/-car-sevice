package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Product;
import com.notfound.carservice.domain.ProductDetail;
import com.notfound.carservice.model.adapter.ProductAdapter;
import com.notfound.carservice.model.adapter.ProductDetailAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateProductRequest;
import com.notfound.carservice.model.request.UpdateProductRequest;
import com.notfound.carservice.model.response.CreateProductResponse;
import com.notfound.carservice.model.response.GetProductResponse;
import com.notfound.carservice.model.response.UpdateProductResponse;
import com.notfound.carservice.repository.ProductDetailRepository;
import com.notfound.carservice.repository.ProductReponsitory;
import com.notfound.carservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductReponsitory productReponsitory;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductDetailAdapter productDetailAdapter;

    @Autowired
    ProductAdapter productAdapter;

    @Override
    public BaseResponseApi<CreateProductResponse> createProduct(CreateProductRequest request) {
        BaseResponseApi<CreateProductResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Product foundProduct = productReponsitory.findByProductByCreate(request.getProductName());
            if (foundProduct != null) {
                responseStatus.setFailed("Sản phẩm đã tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Product product = productReponsitory.save(productAdapter.createProduct(request));
            if (product == null) {
                responseStatus.setFailed("Tạo sản phẩm không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            // tạo detail product
            ProductDetail productDetail =productDetailRepository.save(productDetailAdapter.createProductDetail(request, product));

            responseApi.setData(productAdapter.buildCreateProduct(product,productDetail));
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
    public BaseResponseApi<UpdateProductResponse> updateProduct(UpdateProductRequest request) {
        BaseResponseApi<UpdateProductResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Product foundProduct = productReponsitory.findByProductByUpdate(request.getId(), request.getProductName());
            if (foundProduct != null) {
                responseStatus.setFailed("Sản phẩm không tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Product product = productReponsitory.save(productAdapter.UpdateProduct(request));

            if (product == null) {
                responseStatus.setFailed("Cập nhật không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(productAdapter.buildUpdateProduct(product));
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
    public BaseResponseApi<List<GetProductResponse>> getAllProduct() {
        BaseResponseApi<List<GetProductResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetProductResponse> allProductResponseList = new ArrayList<>();

        try {
            List<Product> productList = productReponsitory.findAll();

            for (Product product :
                    productList) {
                if (product.getDeleteAt() == null) {
                    allProductResponseList.add(productAdapter.buildGetAllProduct(product));
                }
            }

            responseApi.setData(allProductResponseList);
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
    public BaseResponseApi<GetProductResponse> findById(Integer id) {
        BaseResponseApi<GetProductResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Optional<Product> product = productReponsitory.findById(id);
            if (product.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy sản phẩm có id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseStatus.setSuccess();
            responseApi.setData(productAdapter.buildGetAllProduct(product.get()));
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
    public BaseResponseApi<GetProductResponse> deleteById(Integer id) {
        BaseResponseApi<GetProductResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Product> product = productReponsitory.findById(id);
            if (product.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy sản phẩm có id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            product.get().setDeleteAt(LocalDateTime.now());

            Product deleteProduct = productReponsitory.save(product.get());

            if (deleteProduct == null){
                responseStatus.setFailed("Xoá sản phẩm không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(productAdapter.buildGetAllProduct(deleteProduct));
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