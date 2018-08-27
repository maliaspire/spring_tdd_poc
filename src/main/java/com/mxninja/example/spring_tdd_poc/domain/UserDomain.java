package com.mxninja.example.spring_tdd_poc.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Document(collection = "Users")
public class UserDomain {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private Integer age;

    private List<String> emails;

    private List<String> contactNumbers;

}
