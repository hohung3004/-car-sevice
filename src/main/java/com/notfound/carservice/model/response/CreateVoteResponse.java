package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateVoteResponse {
    private Integer idUser;

    private String comment;

    private Integer starRaiting;

    private String status;
}

