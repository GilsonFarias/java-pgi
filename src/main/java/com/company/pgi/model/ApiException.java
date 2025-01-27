package com.company.pgi.model;

public class ApiException {
    private String message;
    private String timestamp;

    public ApiException( String message) {
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
