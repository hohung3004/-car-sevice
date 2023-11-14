package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Brand;
import com.notfound.carservice.model.adapter.BrandAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateBrandRequest;
import com.notfound.carservice.model.request.UpdateBrandRequest;
import com.notfound.carservice.model.response.CreateBrandResponse;
import com.notfound.carservice.model.response.GetBrandResponse;
import com.notfound.carservice.model.response.UpdateBrandResponse;
import com.notfound.carservice.repository.BrandReponsitory;
import com.notfound.carservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandReponsitory brandReponsitory;

    @Autowired
    BrandAdapter brandAdapter;

    @Override
    public BaseResponseApi<CreateBrandResponse> createBrand(CreateBrandRequest request) {
        BaseResponseApi<CreateBrandResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            Brand foundBrand = brandReponsitory.findBrandByCreate(request.getBrandName());
            if (foundBrand != null) {
                responseStatus.setFailed("Thương hiệu đã tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            Brand brand = brandReponsitory.save(brandAdapter.createBrand(request));
            if (brand == null) {
                responseStatus.setFailed("Tạo brand không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(brandAdapter.buildCreateBrand(brand));
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
    public BaseResponseApi<UpdateBrandResponse> updateBrand(UpdateBrandRequest request) {
        BaseResponseApi<UpdateBrandResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Brand foundBrand = brandReponsitory.findBrandByUpdate(request.getBrandName(), request.getId());
            if (foundBrand != null) {
                responseStatus.setFailed("Brand không tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            Brand brand = brandReponsitory.save(brandAdapter.updateBrand(request));
            if (brand == null) {
                responseStatus.setFailed("Cập nhật không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(brandAdapter.buildUpdateBrand(brand));
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
    public BaseResponseApi<List<GetBrandResponse>> getAllBrand() {
        BaseResponseApi<List<GetBrandResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetBrandResponse> allBrandResponseList = new ArrayList<>();
        try {
            // jpa getall
            List<Brand> brandList = brandReponsitory.findAll();

            for (Brand brand :
                    brandList) {
                if (brand.getDeleteAt() == null) {
                    allBrandResponseList.add(brandAdapter.buildGetAllBrandResponse(brand));
                }
            }

            responseApi.setData(allBrandResponseList);
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
    public BaseResponseApi<GetBrandResponse> findById(Integer id) {
        BaseResponseApi<GetBrandResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Brand> brand = brandReponsitory.findById(id);
            if (brand.isEmpty()) {
                responseStatus.setNotFound("Không tìm thấy brand theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseStatus.setSuccess();
            responseApi.setData(brandAdapter.buildGetAllBrandResponse(brand.get()));
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
    public BaseResponseApi<GetBrandResponse> deleteFindId(Integer id) {
        BaseResponseApi<GetBrandResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Brand> brand = brandReponsitory.findById(id);
            if (brand.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy brand theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            brand.get().setDeleteAt(LocalDateTime.now());

            Brand deleteBrand = brandReponsitory.save(brand.get());
            if (deleteBrand == null) {
                responseStatus.setFailed("Xoá brand không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(brandAdapter.buildGetAllBrandResponse(deleteBrand));
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
