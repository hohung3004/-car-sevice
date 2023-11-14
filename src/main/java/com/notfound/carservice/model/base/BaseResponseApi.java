package com.notfound.carservice.model.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponseApi<T> implements Serializable {

    private BaseResponseStatus responseStatus;

    private T data;

}
