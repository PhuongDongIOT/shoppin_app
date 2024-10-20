package com.hcm.base;

public class BaseResponse<T> {

    private boolean success;
    private T data;
    private Object error;

    public BaseResponse(boolean success, T data, Object errors) {
        this.success = success;
        this.data = data;
        this.error = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getErrors() {
        return error;
    }

    public void setErrors(Object errors) {
        this.error = errors;
    }
}
