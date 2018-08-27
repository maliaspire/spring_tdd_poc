package com.mxninja.example.spring_tdd_poc.models;

import lombok.Data;

import java.util.List;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@Data
public class UserModel {

    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> email;
    private List<String> contentNumber;

}
