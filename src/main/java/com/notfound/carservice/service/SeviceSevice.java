package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreateSeviceRequest;
import com.notfound.carservice.model.request.UpdateSeviceRequest;
import com.notfound.carservice.model.response.CreateSeviceResponse;
import com.notfound.carservice.model.response.GetSeviceReponse;
import com.notfound.carservice.model.response.UpdateSeviceReponse;

import java.util.List;

public interface SeviceSevice {

    BaseResponseApi<CreateSeviceResponse> createSevice (CreateSeviceRequest request);
    BaseResponseApi<UpdateSeviceReponse> updateSevice (UpdateSeviceRequest request);
    BaseResponseApi<List<GetSeviceReponse>> getAllSevice();
    BaseResponseApi<GetSeviceReponse> finbyId(Integer id);
    BaseResponseApi<GetSeviceReponse> deleteById(Integer id);
}
