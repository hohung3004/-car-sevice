package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Sevice;
import com.notfound.carservice.model.adapter.SeviceAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateSeviceRequest;
import com.notfound.carservice.model.request.UpdateSeviceRequest;
import com.notfound.carservice.model.response.CreateSeviceResponse;
import com.notfound.carservice.model.response.GetSeviceReponse;
import com.notfound.carservice.model.response.UpdateSeviceReponse;
import com.notfound.carservice.repository.SeviceRepository;
import com.notfound.carservice.service.SeviceSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeviceSeviceImpl implements SeviceSevice {

    @Autowired
    SeviceRepository seviceRepository;
    @Autowired
    SeviceAdapter seviceAdapter;

    @Override
    public BaseResponseApi<CreateSeviceResponse> createSevice(CreateSeviceRequest request) {
        BaseResponseApi<CreateSeviceResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            Sevice foundSevice = seviceRepository.findSeviceByTitle(request.getTitle());
            if (foundSevice != null) {
                responseStatus.setFailed("Dịch vụ đã tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Sevice sevice = seviceRepository.save(seviceAdapter.createSvice(request));
            if (sevice == null) {
                responseStatus.setFailed("Tạo dịch vụ không thành công");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(seviceAdapter.buildCreateSevice(sevice));
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
    public BaseResponseApi<UpdateSeviceReponse> updateSevice(UpdateSeviceRequest request) {
        BaseResponseApi<UpdateSeviceReponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Sevice foundSevice = seviceRepository.findSeviceByUpdate(request.getTitle(),request.getId());
            if (foundSevice != null){
                responseStatus.setFailed("Dịch vụ không tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            Sevice sevice = seviceRepository.save(seviceAdapter.updateSevice(request));
            if(sevice == null){
                responseStatus.setFailed("Cập nhật không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(seviceAdapter.buildUpdateSevice(sevice));
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
    public BaseResponseApi<List<GetSeviceReponse>> getAllSevice() {
        BaseResponseApi<List<GetSeviceReponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetSeviceReponse> allSeviceList = new ArrayList<>();
        try {
            List<Sevice> sevices = seviceRepository.findAll();

            for (Sevice sevice :
                    sevices) {
                if (sevice.getDeleteAt()  == null) {
                    allSeviceList.add(seviceAdapter.buildGetAllSeviceResponse(sevice));
                }
            }

            responseApi.setData(allSeviceList);
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
    public BaseResponseApi<GetSeviceReponse> finbyId(Integer id) {
        BaseResponseApi<GetSeviceReponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Sevice> sevice = seviceRepository.findById(id);
            if (sevice.isEmpty()) {
                responseStatus.setNotFound("Không tìm thấy dịch vụ theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseStatus.setSuccess();
            responseApi.setData(seviceAdapter.buildGetAllSeviceResponse(sevice.get()));
            responseApi.setResponseStatus(responseStatus);
            return responseApi;
        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<GetSeviceReponse> deleteById(Integer id) {
        BaseResponseApi<GetSeviceReponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Sevice> sevice = seviceRepository.findById(id);
            if (sevice.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy dịch vụ theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            sevice.get().setDeleteAt(LocalDateTime.now());

            Sevice deleteSevice = seviceRepository.save(sevice.get());
            if (deleteSevice == null) {
                responseStatus.setFailed("Xoá dịch vụ không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(seviceAdapter.buildGetAllSeviceResponse(deleteSevice));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }


}
