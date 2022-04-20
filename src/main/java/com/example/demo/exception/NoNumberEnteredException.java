package com.example.demo.exception;

public class NoNumberEnteredException extends RuntimeException {

    public NoNumberEnteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNumberEnteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoNumberEnteredException(Throwable cause) {
        super(cause);
    }

    public NoNumberEnteredException(String message) {
        super(message);
    }

    public NoNumberEnteredException() {
    }

}
