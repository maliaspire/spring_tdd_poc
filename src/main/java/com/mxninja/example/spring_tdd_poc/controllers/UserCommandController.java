package com.mxninja.example.spring_tdd_poc.controllers;

import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import com.mxninja.example.spring_tdd_poc.exception.NotValidException;
import com.mxninja.example.spring_tdd_poc.models.ApiStatus;
import com.mxninja.example.spring_tdd_poc.models.ResponseModel;
import com.mxninja.example.spring_tdd_poc.models.UserModel;
import com.mxninja.example.spring_tdd_poc.repo.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@RestController
@RequestMapping("users")
public class UserCommandController {

    private final UserDao USER_DAO;

    @Autowired
    public UserCommandController(UserDao userDao) {
        USER_DAO = userDao;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<UserDomain> saveUser(@RequestBody UserModel reqBody) {
        try {
            UserDomain userDomain = new UserDomain();
            userDomain.setFirstName(reqBody.getFirstName());
            userDomain.setLastName(reqBody.getLastName());
            userDomain.setAge(reqBody.getAge());
            userDomain.setEmails(reqBody.getEmail());
            userDomain.setContactNumbers(reqBody.getContentNumber());
            return new ResponseModel<>(ApiStatus.CREATED, USER_DAO.save(userDomain));
        } catch (RuntimeException e) {
            return new ResponseModel<>(ApiStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseModel<>(ApiStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
