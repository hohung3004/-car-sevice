package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreateBrandRequest;
import com.notfound.carservice.model.request.UpdateBrandRequest;
import com.notfound.carservice.model.response.CreateBrandResponse;
import com.notfound.carservice.model.response.GetBrandResponse;
import com.notfound.carservice.model.response.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    BaseResponseApi<CreateBrandResponse> createBrand(CreateBrandRequest request);
    BaseResponseApi<UpdateBrandResponse> updateBrand(UpdateBrandRequest request);
    BaseResponseApi<List<GetBrandResponse>> getAllBrand();
    BaseResponseApi<GetBrandResponse> findById(Integer id);
    BaseResponseApi<GetBrandResponse> deleteFindId(Integer id);
}
