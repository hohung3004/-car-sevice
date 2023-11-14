package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreateWishListRequest;
import com.notfound.carservice.model.response.CreateWishListResponse;
import com.notfound.carservice.model.response.GetWishListResponse;

import java.util.List;

public interface WishlistSevice {

    BaseResponseApi<CreateWishListResponse> createWishList (CreateWishListRequest request);
    BaseResponseApi<List<GetWishListResponse>> getAllWishList();
    BaseResponseApi<GetWishListResponse> deleteById(Integer id);
}
