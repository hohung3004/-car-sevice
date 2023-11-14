package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSeviceReponse {

    private Integer id;

    private String icon;

    private String title;

    private String summary;

    private String status;
}
