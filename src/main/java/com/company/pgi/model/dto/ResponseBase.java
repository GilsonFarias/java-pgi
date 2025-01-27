package com.company.pgi.model.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseBase<T> {
    private HttpStatus statusCode;
    private String message;
    private String version = "v1.0.0.0";
    private List<T> data;

    public ResponseBase(){
    }

    public ResponseBase(String message, HttpStatus statusCode, String version, List<T> data) {
        this.message = message;
        this.statusCode = statusCode;
        this.version = version;
        this.data = data;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
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
}
