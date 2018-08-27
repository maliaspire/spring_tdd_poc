package com.mxninja.example.spring_tdd_poc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */
@Getter
@AllArgsConstructor
public enum ApiStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "No Content"),
    BAD_REQUEST(400, "Bad Request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private int code;
    private String message;

}
