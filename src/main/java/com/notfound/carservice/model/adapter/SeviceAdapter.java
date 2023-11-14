package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.Sevice;
import com.notfound.carservice.model.request.CreateSeviceRequest;
import com.notfound.carservice.model.request.UpdateSeviceRequest;
import com.notfound.carservice.model.response.CreateSeviceResponse;
import com.notfound.carservice.model.response.GetSeviceReponse;
import com.notfound.carservice.model.response.UpdateSeviceReponse;
import org.springframework.stereotype.Component;

@Component
public class SeviceAdapter {
    public Sevice createSvice(CreateSeviceRequest request) {
        return Sevice.builder()
                .icon(request.getIcon())
                .title(request.getTitle())
                .summary(request.getSummary())
                .status("1")
                .build();
    }

    public CreateSeviceResponse buildCreateSevice(Sevice s){
        return CreateSeviceResponse.builder()
                .icon(s.getIcon())
                .title(s.getTitle())
                .summary(s.getSummary())
                .status(s.getStatus())
                .build();
    }

    public Sevice updateSevice (UpdateSeviceRequest request){
        return Sevice.builder()
                .icon(request.getIcon())
                .title(request.getTitle())
                .summary(request.getSummary())
                .status(request.getStatus())
                .build();
    }

    public UpdateSeviceReponse buildUpdateSevice(Sevice s){
        return UpdateSeviceReponse.builder()
                .icon(s.getIcon())
                .title(s.getTitle())
                .summary(s.getSummary())
                .status(s.getStatus())
                .build();
    }

    public GetSeviceReponse buildGetAllSeviceResponse(Sevice sevice){
        return GetSeviceReponse.builder()
                .id(sevice.getId())
                .icon(sevice.getIcon())
                .title(sevice.getTitle())
                .summary(sevice.getSummary())
                .status(sevice.getStatus())
                .build();
    }
}
