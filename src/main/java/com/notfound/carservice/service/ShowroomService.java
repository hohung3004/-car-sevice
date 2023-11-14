package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreateShowroomRequest;
import com.notfound.carservice.model.request.UpdateShowroomRequest;
import com.notfound.carservice.model.response.CreateShowRoomResponse;
import com.notfound.carservice.model.response.GetShowroomResponse;
import com.notfound.carservice.model.response.UpdateShowroomResponse;

import java.util.List;


public interface ShowroomService {
    BaseResponseApi<CreateShowRoomResponse> createShowroom(CreateShowroomRequest request);
    BaseResponseApi<UpdateShowroomResponse> updateShowroom(UpdateShowroomRequest request);
    BaseResponseApi<List<GetShowroomResponse>> getAllShowroom();
    BaseResponseApi<GetShowroomResponse> findById(Integer id);
    BaseResponseApi<GetShowroomResponse> deleteById(Integer id);
}
