package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetBrandResponse {

    private Integer id;

    private String brandName;

    private String logo;

    private String status;

}
