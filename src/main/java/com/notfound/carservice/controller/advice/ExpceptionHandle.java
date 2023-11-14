package com.notfound.carservice.controller.advice;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExpceptionHandle extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponseApi<Object> BaseResponseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) ->{
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, message);
            });

            responseStatus.setInValid("Vui lòng điền đầy đầy đủ thông tin!");
            BaseResponseApi.setResponseStatus(responseStatus);
            BaseResponseApi.setData(errors);

        }
        catch (Exception e) {
            responseStatus.setException(e.getMessage());
            BaseResponseApi.setResponseStatus(responseStatus);
        }
        return new ResponseEntity<Object>(BaseResponseApi, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponseApi<String> BaseResponseApi= new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        responseStatus.setFailed("Vui lòng kiểm tra lại đường dẫn!");
        BaseResponseApi.setResponseStatus(responseStatus);

        return new ResponseEntity<Object>(BaseResponseApi, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponseApi<Object> BaseResponseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        responseStatus.setFailed("Method không được hỗ trợ, vui lòng kiểm tra lại!");
        BaseResponseApi.setResponseStatus(responseStatus);

        return new ResponseEntity<Object>(BaseResponseApi, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        BaseResponseApi<Object> BaseResponseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        responseStatus.setTimeout("Request timeout, vui lòng thử lại sau!");
        BaseResponseApi.setResponseStatus(responseStatus);

        return new ResponseEntity<Object>(BaseResponseApi, HttpStatus.REQUEST_TIMEOUT);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponseApi<Object> BaseResponseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        responseStatus.setException("Có lỗi xãy ra, vui lòng kiểm tra lại!");
        BaseResponseApi.setResponseStatus(responseStatus);

        return new ResponseEntity<Object>(BaseResponseApi, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
