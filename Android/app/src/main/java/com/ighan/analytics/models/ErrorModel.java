package com.ighan.analytics.models;

public class ErrorModel {

    private String message;

    private Throwable error;

    private String key;

    private String value;

    public ErrorModel(String message, String key, String value) {
        this.message = message;
        this.key = key;
        this.value = value;
    }

    public ErrorModel(Throwable error, String key, String value) {
        this.error = error;
        this.key = key;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getError() {
        return error;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
