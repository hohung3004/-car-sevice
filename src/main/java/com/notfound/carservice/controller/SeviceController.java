package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateSeviceRequest;
import com.notfound.carservice.model.request.UpdateSeviceRequest;
import com.notfound.carservice.model.response.CreateSeviceResponse;
import com.notfound.carservice.model.response.GetSeviceReponse;
import com.notfound.carservice.model.response.UpdateSeviceReponse;
import com.notfound.carservice.service.SeviceSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/sevice")
public class SeviceController {
    @Autowired
    SeviceSevice seviceSevice;

    @PostMapping("/create")
    public BaseResponseApi<CreateSeviceResponse> create(@Valid @RequestBody CreateSeviceRequest request) {
        BaseResponseApi<CreateSeviceResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        // call service
        try {
            responseApi = seviceSevice.createSevice(request);
            return responseApi;
        }
        catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @PostMapping("/update")
    public BaseResponseApi<UpdateSeviceReponse> update (@Valid @RequestBody UpdateSeviceRequest request){
        BaseResponseApi<UpdateSeviceReponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = seviceSevice.updateSevice(request);
            return responseApi;
        }catch (Exception e ){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
    return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetSeviceReponse>> getAllSevice() {
        BaseResponseApi<List<GetSeviceReponse>>responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = seviceSevice.getAllSevice();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/{id}")
    public BaseResponseApi<GetSeviceReponse> findSeviceById(@PathVariable Integer id){
        BaseResponseApi<GetSeviceReponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = seviceSevice.finbyId(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @DeleteMapping("/{id}")
    public BaseResponseApi<GetSeviceReponse> deleteById(@PathVariable Integer id){
        BaseResponseApi<GetSeviceReponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = seviceSevice.deleteById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
}
