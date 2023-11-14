package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateBrandRequest;
import com.notfound.carservice.model.request.UpdateBrandRequest;
import com.notfound.carservice.model.response.CreateBrandResponse;
import com.notfound.carservice.model.response.GetBrandResponse;
import com.notfound.carservice.model.response.UpdateBrandResponse;
import com.notfound.carservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @PostMapping("/create")
    public BaseResponseApi<CreateBrandResponse> create(@Valid @RequestBody CreateBrandRequest request) {
        BaseResponseApi<CreateBrandResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        // call service
        try {
            responseApi = brandService.createBrand(request);
            return responseApi;
        }
        catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi. setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @PostMapping( "/update")
    BaseResponseApi<UpdateBrandResponse> update(@Valid @RequestBody UpdateBrandRequest request){
        BaseResponseApi<UpdateBrandResponse>responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = brandService.updateBrand(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetBrandResponse>> getAllBrand() {
        BaseResponseApi<List<GetBrandResponse>>responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = brandService.getAllBrand();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/{id}")
    public BaseResponseApi<GetBrandResponse> findBrandById(@PathVariable Integer id){
        BaseResponseApi<GetBrandResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
           responseApi = brandService.findById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @DeleteMapping("/{id}")
    public BaseResponseApi<GetBrandResponse> deleteById(@PathVariable Integer id){
        BaseResponseApi<GetBrandResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = brandService.deleteFindId(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

}
