package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.User;
import com.notfound.carservice.model.request.LoginRequest;
import com.notfound.carservice.model.request.RegisterRequest;
import com.notfound.carservice.model.response.LoginResponse;
import com.notfound.carservice.model.response.RegisterResponse;
import com.notfound.carservice.utils.EncodeUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserAdapter {

    public LoginRequest loginRequest(User user) {
        return LoginRequest.builder().email(user.getEmail()).password(user.getPassword()).build();
    }

    public User buildUser(LoginRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(EncodeUtils.md5(request.getPassword()))
                .createAt(LocalDateTime.now())
                .build();
    }

    public User buildUser(RegisterRequest request) {
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(EncodeUtils.md5(request.getPassword()))
                .role("1")
                .status("1")
                .createAt(LocalDateTime.now())
                .build();
    }

    public LoginResponse buildLoginResp(User u) {
        return LoginResponse.builder()
                .fullName(u.getFullName())
                .email(u.getEmail())
                .status(u.getStatus())
                .role(u.getRole())
                .build();
    }

    public RegisterResponse buildRegister(User u){
        return RegisterResponse.builder()
                .fullName(u.getFullName())
                .email(u.getEmail())
                .role(u.getRole())
                .status(u.getStatus())
                .build();
    }

}
