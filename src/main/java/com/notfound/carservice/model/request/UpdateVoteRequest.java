package com.notfound.carservice.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class UpdateVoteRequest {

    private Integer idUser;

    @NotNull(message = "Id không được để trống")
    private Integer id;

    @NotBlank(message = "commemt không được để trống!")
    private String comment;

    @NotNull(message = "starRaiting không được để trống!")
    private Integer starRaiting;

    @NotBlank(message = "status không được để trống!")
    private String status;

    private LocalDateTime updateAt;

}
