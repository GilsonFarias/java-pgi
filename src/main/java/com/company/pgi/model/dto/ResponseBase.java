package com.company.pgi.model.dto;

import java.util.List;

import com.company.pgi.model.ApiError;

public class ResponseBase<T> {
    private String statusCode;
    private String message;
    private String version = "v1.0.0.0";
    private List<T> data;
    private List<ApiError> apiError;

    public ResponseBase(){
    }

    public ResponseBase(String message, String statusCode, String version, List<T> data, List<ApiError> apiError) {
        this.message = message;
        this.statusCode = statusCode;
        this.version = version;
        this.data = data;
        this.apiError = apiError;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<ApiError> getApiError() {
        return apiError;
    }

    public void setApiError(List<ApiError> apiError) {
        this.apiError = apiError;
    }

}
