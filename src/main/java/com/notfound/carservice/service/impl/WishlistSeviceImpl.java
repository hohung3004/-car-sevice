package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Product;
import com.notfound.carservice.domain.Wishlist;
import com.notfound.carservice.model.adapter.WishListAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateWishListRequest;
import com.notfound.carservice.model.response.*;
import com.notfound.carservice.repository.WishlistRepository;
import com.notfound.carservice.service.WishlistSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistSeviceImpl implements WishlistSevice {

    @Autowired
    private WishlistRepository repository;

    @Autowired
    private WishListAdapter adapter;


    @Override
    public BaseResponseApi<CreateWishListResponse> createWishList(CreateWishListRequest request) {
        BaseResponseApi<CreateWishListResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Wishlist foundWishlist = repository.findWhishlistByCreate(request.getIdProduct(),request.getIdUser());
            if (foundWishlist != null) {
                responseStatus.setFailed("Sản phẩm yêu thích đã tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Wishlist wlist = adapter.createWishList(request);
            Wishlist wishlist = repository.save(wlist);
            if (wishlist == null) {
                responseStatus.setFailed("Thêm sản phẩm yêu thích không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(adapter.buildCreateWishList(wishlist));
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
    public BaseResponseApi<List<GetWishListResponse>> getAllWishList() {
        BaseResponseApi<List<GetWishListResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetWishListResponse> allWishList = new ArrayList<>();

        try {
            List<Wishlist> wishlists = repository.findAll();

            for (Wishlist wishlist :
                    wishlists) {
                if (wishlist.getDeleteAt() == null) {
                    allWishList.add(adapter.buildGetAllWishList(wishlist));
                }
            }

            responseApi.setData(allWishList);
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
    public BaseResponseApi<GetWishListResponse> deleteById(Integer id) {
        BaseResponseApi<GetWishListResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Wishlist> wishlist = repository.findById(id);
            if (wishlist.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy sản phẩm có id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            wishlist.get().setDeleteAt(LocalDateTime.now());

            Wishlist deleteWishlist = repository.save(wishlist.get());

            if (deleteWishlist == null){
                responseStatus.setFailed("Xoá sản phẩm yêu thích không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(adapter.buildGetAllWishList(deleteWishlist));
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
