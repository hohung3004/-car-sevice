package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.LoginRequest;
import com.notfound.carservice.model.request.RegisterRequest;
import com.notfound.carservice.model.response.LoginResponse;
import com.notfound.carservice.model.response.RegisterResponse;

public interface AuthService {

    BaseResponseApi<LoginResponse> login(LoginRequest request);
    BaseResponseApi<RegisterResponse> register(RegisterRequest request);
}
