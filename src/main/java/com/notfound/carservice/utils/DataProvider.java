package com.notfound.carservice.utils;

import com.notfound.carservice.domain.User;
import com.notfound.carservice.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataProvider {

    @Autowired
    UserRepository userRepository;

    public void createDataTest(Boolean isCreate) {
        if (isCreate) {
            createDataUser(5) ;
        }
    }

    public void createDataUser(int limit) {

        User user = User.builder()
                .fullName("ChinhDev")
                .email("hochinh302@gmail.com")
                .password(EncodeUtils.md5("123456"))
                .status("1")
                .createAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

    }
}
