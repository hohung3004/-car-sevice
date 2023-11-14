package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.User;
import com.notfound.carservice.model.adapter.UserAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.LoginRequest;
import com.notfound.carservice.model.request.RegisterRequest;
import com.notfound.carservice.model.response.LoginResponse;
import com.notfound.carservice.model.response.RegisterResponse;
import com.notfound.carservice.repository.UserRepository;
import com.notfound.carservice.service.AuthService;
import com.notfound.carservice.utils.EncodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAdapter userAdapter;

    @Override
    public BaseResponseApi<LoginResponse> login(LoginRequest request) {
        BaseResponseApi<LoginResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            // xu ly logic check user
            User user = userRepository.login(request.getEmail(), EncodeUtils.md5(request.getPassword()));

            if (user == null) {
                responseStatus.setFailed("Đăng nhập không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(userAdapter.buildLoginResp(user));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
            return responseApi;
        }
        catch (Exception e) {
            // Xu ly ngoai
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }

        return responseApi;
    }

    @Override
    public BaseResponseApi<RegisterResponse> register(RegisterRequest request) {
        BaseResponseApi<RegisterResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            // validate email unique
            User foundUser = userRepository.findUserByEmail(request.getEmail());
            if (foundUser != null) {
                responseStatus.setFailed("Email đã tồn tại trên hệ thống!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }


            User user = userRepository.save(userAdapter.buildUser(request));

            if (user == null){
                responseStatus.setFailed("Đăng ký không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(userAdapter.buildRegister(user));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
}
