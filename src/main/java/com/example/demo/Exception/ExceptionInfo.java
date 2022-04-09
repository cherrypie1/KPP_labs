package com.example.demo.Exception;

public class ExceptionInfo {
    private int code;
    private String message;


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public ExceptionInfo(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
