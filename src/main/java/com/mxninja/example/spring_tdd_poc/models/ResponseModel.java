package com.mxninja.example.spring_tdd_poc.models;

import lombok.Data;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@Data
public class ResponseModel<T> {

    private int code;
    private String message;
    private T data;

    public ResponseModel() {
        this(ApiStatus.OK);
    }

    public ResponseModel(ApiStatus status) {
        this(status.getCode(), status.getMessage());
    }

    public ResponseModel(ApiStatus status, String message) {
        this(status.getCode(), message);
    }

    private ResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
