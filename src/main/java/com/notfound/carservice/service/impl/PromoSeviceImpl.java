package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Promo;
import com.notfound.carservice.model.adapter.PromoAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreatePromoRequest;
import com.notfound.carservice.model.request.UpdatePromoRequest;
import com.notfound.carservice.model.response.CreatePromoResponse;
import com.notfound.carservice.model.response.GetPromoResponse;
import com.notfound.carservice.model.response.UpdatePromoResponse;
import com.notfound.carservice.repository.PromoReponsitory;
import com.notfound.carservice.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  PromoSeviceImpl implements PromoService {
    @Autowired
    PromoReponsitory promoReponsitory;

    @Autowired
    PromoAdapter promoAdapter;

    @Override
    public BaseResponseApi<CreatePromoResponse> createPromo(CreatePromoRequest request) {
        BaseResponseApi<CreatePromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            Promo foundPromo = promoReponsitory.findPromoByCreate(request.getPromotionCode());
            if (foundPromo != null) {
                responseStatus.setFailed("mã khuyến mãi đã tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Promo promo = promoReponsitory.save(promoAdapter.createPromo(request));
            if (promo == null) {
                responseStatus.setFailed("Tạo mã khuyến mãi không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(promoAdapter.buildCreatePromo(promo));
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
    public BaseResponseApi<UpdatePromoResponse> updatePromo(UpdatePromoRequest request) {
        BaseResponseApi<UpdatePromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Promo foundPromo = promoReponsitory.findPromoByUpdate(request.getPromotionCode(), request.getId());
            if (foundPromo != null) {
                responseStatus.setFailed("mã khuyến mãi không tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            Promo promo = promoReponsitory.save(promoAdapter.updatePromo(request));
            if (promo == null) {
                responseStatus.setFailed("Cập nhật không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(promoAdapter.buildUpdatePromo(promo));
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
    public BaseResponseApi<List<GetPromoResponse>> getAllPromo() {
        BaseResponseApi<List<GetPromoResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetPromoResponse> allPromoResponseList = new ArrayList<>();
        try {
            // jpa getall
            List<Promo> promoList = promoReponsitory.findAll();

            for (Promo promo :
                    promoList) {
                if (promo.getDeleteAt() == null) {
                    allPromoResponseList.add(promoAdapter.buildGetAllPromoResponse(promo));
                }
            }

            responseApi.setData(allPromoResponseList);
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
    public BaseResponseApi<GetPromoResponse> findById(Integer id) {
        BaseResponseApi<GetPromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Promo> promo = promoReponsitory.findById(id);
            if (promo.isEmpty()) {
                responseStatus.setNotFound("Không tìm thấy promo theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseStatus.setSuccess();
            responseApi.setData(promoAdapter.buildGetAllPromoResponse(promo.get()));
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
    public BaseResponseApi<GetPromoResponse> deleteFindId(Integer id) {
        BaseResponseApi<GetPromoResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Promo> promo = promoReponsitory.findById(id);
            if (promo.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy promo theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            promo.get().setDeleteAt(LocalDateTime.now());

            Promo deletePromo = promoReponsitory.save(promo.get());
            if (deletePromo == null) {
                responseStatus.setFailed("Xoá promo không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(promoAdapter.buildGetAllPromoResponse(deletePromo));
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
