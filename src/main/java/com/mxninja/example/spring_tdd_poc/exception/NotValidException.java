package com.mxninja.example.spring_tdd_poc.exception;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */
public class NotValidException extends RuntimeException {

    public NotValidException() {
    }

    public NotValidException(String message) {
        super(message);
    }
}
