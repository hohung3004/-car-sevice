package com.notfound.carservice.model.base;

import com.notfound.carservice.constant.Constants;
import lombok.Data;

@Data
public class BaseResponseStatus {

    private String resp;

    private String respCode;

    private String respDesc;


    public void setSuccess() {
        this.respCode = Constants.ECODE.SUCCESS;
        this.respDesc = Constants.SUCCESS_RESPONSE;
        this.resp = "Thực hiện thành công!";
    }

    public void setSuccess(String message) {
        this.respCode = Constants.ECODE.SUCCESS;
        this.respDesc = Constants.SUCCESS_RESPONSE;

        if (message != null) {
            this.resp = message;
        } else {
            this.resp = "Thực hiện thành công!";
        }
    }

    public void setException() {
        this.respCode = Constants.ECODE.EXCEPTION;
        this.respDesc = Constants.EXCEPTION_RESPONSE;
        this.resp = "Có ngoại lệ xãy ra khi thực hiện!";
    }

    public void setException(String message) {
        this.respCode = Constants.ECODE.EXCEPTION;
        this.respDesc = Constants.EXCEPTION_RESPONSE;

        if (message != null) {
            this.resp = message;
        } else {
            this.resp = "Có ngoại lệ xãy ra khi thực hiện!";
        }
    }

    public void setFailed() {
        this.respCode = Constants.ECODE.FAILED;
        this.respDesc = Constants.FAILED_RESPONSE;
        this.resp = "Có lỗi xãy ra khi thực hiện!";
    }

    public void setFailed(String message) {
        this.respCode = Constants.ECODE.FAILED;
        this.respDesc = Constants.FAILED_RESPONSE;

        if (message != null) {
            this.resp = message;
        } else {
            this.resp = "Có lỗi xãy ra khi thực hiện!";
        }
    }

    public void setNotFound() {
        this.respCode = Constants.ECODE.NOT_FOUND;
        this.respDesc = Constants.NOT_FOUND_RESPONSE;
        this.resp = "Không tồn tại kết quả phù hợp!";
    }

    public void setNotFound(String message) {
        this.respCode = Constants.ECODE.NOT_FOUND;
        this.respDesc = Constants.NOT_FOUND_RESPONSE;

        if (message != null) {
            this.resp = message;
        } else {
            this.resp = "Không tồn tại kết quả phù hợp!";
        }
    }

    public void setInValid() {
        this.respCode = Constants.ECODE.IN_VALID;
        this.respDesc = Constants.IN_VALID_RESPONSE;
        this.resp = "Đầu vào không hợp lệ!";
    }

    public void setInValid(String message) {
        this.respCode = Constants.ECODE.IN_VALID;
        this.respDesc = Constants.IN_VALID_RESPONSE;

        if (message != null) {
            this.resp = message;
        } else {
            this.resp = "Đầu vào không hợp lệ!";
        }
    }

    public void setTimeout() {
        this.respCode = Constants.ECODE.TIMEOUT;
        this.respDesc = Constants.TIMEOUT_RESPONSE;
        this.resp = "Hết thời hạn yêu cầu!";
    }

    public void setTimeout(String message) {
        this.respCode = Constants.ECODE.TIMEOUT;
        this.respDesc = Constants.TIMEOUT_RESPONSE;

        if (message != null) {
            this.resp = message;
        } else {
            this.resp = "Hết thời hạn yêu cầu!";
        }
    }
}
