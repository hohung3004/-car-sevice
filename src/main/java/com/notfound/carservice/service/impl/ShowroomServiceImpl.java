package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Showroom;
import com.notfound.carservice.model.adapter.ShowroomAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateShowroomRequest;
import com.notfound.carservice.model.request.UpdateShowroomRequest;
import com.notfound.carservice.model.response.CreateShowRoomResponse;
import com.notfound.carservice.model.response.GetShowroomResponse;
import com.notfound.carservice.model.response.UpdateShowroomResponse;
import com.notfound.carservice.repository.ShowroomReponsitory;
import com.notfound.carservice.service.ShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowroomServiceImpl implements ShowroomService {

    @Autowired
    ShowroomReponsitory showroomReponsitory;

    @Autowired
    ShowroomAdapter showroomAdapter;

    @Override
    public BaseResponseApi<CreateShowRoomResponse> createShowroom(CreateShowroomRequest request) {
        BaseResponseApi<CreateShowRoomResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            Showroom foundShowroom = showroomReponsitory.findShowroomByCrate(request.getShowName());

            if (foundShowroom != null) {

                responseStatus.setFailed("Showroom đã tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Showroom showroom = showroomReponsitory.save(showroomAdapter.createShowroom(request));
            if (showroom == null) {

                responseStatus.setFailed("Tạo showroom không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseApi.setData(showroomAdapter.buildCreateShowrom(showroom));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<UpdateShowroomResponse> updateShowroom(UpdateShowroomRequest request) {
            BaseResponseApi<UpdateShowroomResponse> responseApi = new BaseResponseApi<>();
            BaseResponseStatus responseStatus = new BaseResponseStatus();
            try {
                Showroom foundShowroom = showroomReponsitory.findShowroomByUpdate(request.getId(),request.getShowName());
                if (foundShowroom != null){
                    responseStatus.setFailed("Showroom không tồn tại!");
                    responseApi.setResponseStatus(responseStatus);
                    return responseApi;
                }

                Showroom showroom = showroomReponsitory.save(showroomAdapter.updateShowroom(request));
                if (showroom == null){
                    responseStatus.setFailed("Cập nhật showroom không thành công!");
                    responseApi.setResponseStatus(responseStatus);
                    return responseApi;
                }

                responseApi.setData(showroomAdapter.buildUpdateShowroom(showroom));
                responseStatus.setSuccess();
                responseApi.setResponseStatus(responseStatus);

            }catch (Exception e){
                e.getMessage();
                responseStatus.setException();
                responseApi.setResponseStatus(responseStatus);
            }
            return responseApi;
        }

    @Override
    public BaseResponseApi<List<GetShowroomResponse>> getAllShowroom() {
        BaseResponseApi<List<GetShowroomResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetShowroomResponse> allShowroomResponseList = new ArrayList<>();
        try {
            List<Showroom> showroomList = showroomReponsitory.findAll();

            for (Showroom showroom:
                 showroomList) {
                if (showroom.getDeleteAt() == null) {
                    allShowroomResponseList.add(showroomAdapter.buildGetAllShowroom(showroom));
                }
            }
            responseApi.setData(allShowroomResponseList);
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

    @Override
    public BaseResponseApi<GetShowroomResponse> findById(Integer id) {
        BaseResponseApi<GetShowroomResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
       try {
           Optional<Showroom> showroom = showroomReponsitory.findById(id);
           if (showroom.isEmpty()){
               responseStatus.setNotFound("Không tìm thấy showroom có id = " + id);
               responseApi.setResponseStatus(responseStatus);
               return responseApi;
           }

           responseStatus.setSuccess();
           responseApi.setData(showroomAdapter.buildGetAllShowroom(showroom.get()));
           responseApi.setResponseStatus(responseStatus);
           return responseApi;
       }catch (Exception e){
           e.getMessage();
           responseStatus.setException();
           responseApi.setResponseStatus(responseStatus);
       }
        return responseApi;
    }

    @Override
    public BaseResponseApi<GetShowroomResponse> deleteById(Integer id) {
        BaseResponseApi<GetShowroomResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Optional<Showroom> showroom = showroomReponsitory.findById(id);
            if (showroom.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy showroom có id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            showroom.get().setDeleteAt(LocalDateTime.now());

            Showroom deleteShowroom = showroomReponsitory.save(showroom.get());
            if (deleteShowroom == null ){
                responseStatus.setFailed("Xoá showroom không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(showroomAdapter.buildGetAllShowroom(deleteShowroom));
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
