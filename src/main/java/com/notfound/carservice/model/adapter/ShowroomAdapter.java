package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.Showroom;
import com.notfound.carservice.model.request.CreateShowroomRequest;
import com.notfound.carservice.model.request.UpdateShowroomRequest;
import com.notfound.carservice.model.response.CreateShowRoomResponse;
import com.notfound.carservice.model.response.GetShowroomResponse;
import com.notfound.carservice.model.response.UpdateBrandResponse;
import com.notfound.carservice.model.response.UpdateShowroomResponse;
import org.springframework.stereotype.Component;

@Component
public class ShowroomAdapter {

    public Showroom createShowroom(CreateShowroomRequest request){
        return Showroom.builder()
                .showName(request.getShowName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .status("1")
                .build();
    }

    public Showroom updateShowroom(UpdateShowroomRequest request){
        return Showroom.builder()
                .id(request.getId())
                .showName(request.getShowName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .email(request.getEmail())
                .status(request.getStatus())
                .build();
    }

    public CreateShowRoomResponse buildCreateShowrom(Showroom s){
        return CreateShowRoomResponse.builder()
                .showName(s.getShowName())
                .address(s.getAddress())
                .phone(s.getPhone())
                .email(s.getEmail())
                .status(s.getStatus())
                .build();
    }

    public UpdateShowroomResponse buildUpdateShowroom(Showroom s){
        return UpdateShowroomResponse.builder()
                .showName(s.getShowName())
                .phone(s.getPhone())
                .address(s.getAddress())
                .email(s.getEmail())
                .status(s.getStatus())
                .build();
    }

    public GetShowroomResponse buildGetAllShowroom(Showroom s){
        return GetShowroomResponse.builder()
                .id(s.getId())
                .showName(s.getShowName())
                .address(s.getAddress())
                .phone(s.getPhone())
                .email(s.getEmail())
                .status("1")
                .build();
    }
}
