package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreatePromoRequest;
import com.notfound.carservice.model.request.UpdatePromoRequest;
import com.notfound.carservice.model.response.*;
import com.notfound.carservice.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/promo")
public class PromoController {
    @Autowired
    PromoService promoService;

    @PostMapping("/create")
    public BaseResponseApi<CreatePromoResponse> create(@Valid @RequestBody CreatePromoRequest request) {
        BaseResponseApi<CreatePromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        // call service
        try {
            responseApi = promoService.createPromo(request);
            return responseApi;
        }
        catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi. setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @PostMapping("/update")
    public BaseResponseApi<UpdatePromoResponse> update (@Valid @RequestBody UpdatePromoRequest request){
        BaseResponseApi<UpdatePromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = promoService.updatePromo(request);
            return responseApi;
        }catch (Exception e ){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetPromoResponse>> getAllPromo() {
        BaseResponseApi<List<GetPromoResponse>>responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = promoService.getAllPromo();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/{id}")
    public BaseResponseApi<GetPromoResponse> findPromoById(@PathVariable Integer id){
        BaseResponseApi<GetPromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = promoService.findById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @DeleteMapping("/{id}")
    public BaseResponseApi<GetPromoResponse> deleteById(@PathVariable Integer id){
        BaseResponseApi<GetPromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = promoService.deleteFindId(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
}
