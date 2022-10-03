package com.neoris.neoris.exceptions;

public class ErrorResponse extends Throwable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }
}
