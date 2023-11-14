package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateBrandResponse {

    private String brandName;

    private String logo;

    private String status;
}
