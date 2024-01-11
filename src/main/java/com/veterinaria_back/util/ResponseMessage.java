package com.veterinaria_back.util;

import lombok.Data;

@Data
public class ResponseMessage<T> {

    private int code;
    private String message;
    private T data;

    public ResponseMessage() {
    }

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
