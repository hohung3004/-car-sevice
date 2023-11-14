package com.notfound.carservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSeviceResponse {

    private String icon;

    private String title;

    private String summary;

    private String status;
}
