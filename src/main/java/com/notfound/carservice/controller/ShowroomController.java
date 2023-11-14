package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateShowroomRequest;
import com.notfound.carservice.model.request.UpdateShowroomRequest;
import com.notfound.carservice.model.response.CreateShowRoomResponse;
import com.notfound.carservice.model.response.GetBrandResponse;
import com.notfound.carservice.model.response.GetShowroomResponse;
import com.notfound.carservice.model.response.UpdateShowroomResponse;
import com.notfound.carservice.service.ShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/showroom")
public class ShowroomController {
    @Autowired
    ShowroomService showroomService;

    @PostMapping("/create")
    public BaseResponseApi<CreateShowRoomResponse> create(@Valid @RequestBody CreateShowroomRequest request){
        BaseResponseApi<CreateShowRoomResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = showroomService.createShowroom(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @PostMapping("/update")
    public BaseResponseApi<UpdateShowroomResponse> update(@Valid @RequestBody UpdateShowroomRequest request){
        BaseResponseApi<UpdateShowroomResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = showroomService.updateShowroom(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetShowroomResponse>> getAllShowroom(){
        BaseResponseApi<List<GetShowroomResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = showroomService.getAllShowroom();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/{id}")
    public BaseResponseApi<GetShowroomResponse> findByIdShowroom(@PathVariable Integer id){
        BaseResponseApi<GetShowroomResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = showroomService.findById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @DeleteMapping("/{id}")
    public BaseResponseApi<GetShowroomResponse> deleteByIdShowroom(@PathVariable Integer id){
        BaseResponseApi<GetShowroomResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = showroomService.deleteById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
}
