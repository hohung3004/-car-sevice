package com.notfound.carservice.model.adapter;

import com.notfound.carservice.domain.Brand;
import com.notfound.carservice.model.request.CreateBrandRequest;
import com.notfound.carservice.model.request.UpdateBrandRequest;
import com.notfound.carservice.model.response.CreateBrandResponse;
import com.notfound.carservice.model.response.GetBrandResponse;
import com.notfound.carservice.model.response.UpdateBrandResponse;
import org.springframework.stereotype.Component;

@Component
public class BrandAdapter {

    public Brand createBrand(CreateBrandRequest request) {
        return Brand.builder()
                .brandName(request.getBrandName())
                .logo(request.getLogo())
                .status("1")
                .build();
    }

    public Brand updateBrand(UpdateBrandRequest request){
        return Brand.builder()
                .id(request.getId())
                .brandName(request.getBrandName())
                .logo(request.getLogo())
                .status(request.getStatus())
                .build();
    }

    public CreateBrandResponse buildCreateBrand(Brand b){
        return CreateBrandResponse.builder()
                .brandName(b.getBrandName())
                .logo(b.getLogo())
                .status(b.getStatus())
                .build();
    }

    public UpdateBrandResponse buildUpdateBrand(Brand b){
        return UpdateBrandResponse.builder()
                .brandName(b.getBrandName())
                .logo(b.getLogo())
                .status(b.getStatus())
                .build();
    }

    public GetBrandResponse buildGetAllBrandResponse(Brand brand) {
        return GetBrandResponse.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .logo(brand.getLogo())
                .status(brand.getStatus())
                .build();
    }

}
