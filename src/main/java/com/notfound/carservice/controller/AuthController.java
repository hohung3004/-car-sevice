package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.LoginRequest;
import com.notfound.carservice.model.request.RegisterRequest;
import com.notfound.carservice.model.response.LoginResponse;
import com.notfound.carservice.model.response.RegisterResponse;
import com.notfound.carservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public BaseResponseApi<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        BaseResponseApi<LoginResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            // xu ly logic call service
            responseApi = authService.login(request);
            return responseApi;
        }
        catch (Exception e) {
            // xu ly ngoai le
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }

    @PostMapping("/register")
    public BaseResponseApi<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        BaseResponseApi<RegisterResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus baseResponseStatus = new BaseResponseStatus();
        try {
            responseApi = authService.register(request);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            baseResponseStatus.setException();
            responseApi.setResponseStatus(baseResponseStatus);
        }
        return responseApi;
    }

}
