package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreatePromoRequest;
import com.notfound.carservice.model.request.UpdatePromoRequest;
import com.notfound.carservice.model.response.*;

import java.util.List;

public interface PromoService {
    BaseResponseApi<CreatePromoResponse> createPromo(CreatePromoRequest request);
    BaseResponseApi<UpdatePromoResponse> updatePromo(UpdatePromoRequest request);
    BaseResponseApi<List<GetPromoResponse>> getAllPromo();
    BaseResponseApi<GetPromoResponse> findById(Integer id);
    BaseResponseApi<GetPromoResponse> deleteFindId(Integer id);
}
