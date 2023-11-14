package com.notfound.carservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetVoteResponse {
    private Integer id;

    private String comment;

    private Integer starRaiting;

    private String status;
    private Integer idUser;
}
