package com.company.pgi.dto.responseBase;

public class ResponseBase<T> {
    private String statusCode;
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String version;
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
            
    private T data;
        // Getter e Setter para data
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
