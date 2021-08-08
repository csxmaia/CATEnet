package com.cate.catenet.exception;

import org.springframework.http.HttpStatus;

public class ApiResponseException extends Exception {
    private HttpStatus status;

    public ApiResponseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiResponseException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

}