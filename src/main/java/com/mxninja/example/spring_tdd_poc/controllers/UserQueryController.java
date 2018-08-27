package com.mxninja.example.spring_tdd_poc.controllers;

import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import com.mxninja.example.spring_tdd_poc.exception.NotValidException;
import com.mxninja.example.spring_tdd_poc.models.ApiStatus;
import com.mxninja.example.spring_tdd_poc.models.ResponseModel;
import com.mxninja.example.spring_tdd_poc.repo.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@RestController
@RequestMapping("users")
public class UserQueryController {

    private final UserDao USER_DAO;

    @Autowired
    public UserQueryController(UserDao userDao) {
        USER_DAO = userDao;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<List<UserDomain>> getAll() {
        ResponseModel<List<UserDomain>> respBody = new ResponseModel<>();
        try {
            respBody.setData(USER_DAO.findAll());
            return respBody;
        } catch (Exception e) {
            return new ResponseModel<>(ApiStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<UserDomain> getById(@PathVariable("id") String id) {
        ResponseModel<UserDomain> respBody = new ResponseModel<>();
        try {
            Optional<UserDomain> optional = USER_DAO.findById(id);
            if (!optional.isPresent()) {
                return new ResponseModel<>(ApiStatus.NO_CONTENT);
            }
            respBody.setData(optional.get());
            return respBody;
        } catch (NotValidException e) {
            return new ResponseModel<>(ApiStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseModel<>(ApiStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<List<UserDomain>> getByEmail(@PathVariable("email") String email) {
        ResponseModel<List<UserDomain>> respBody = new ResponseModel<>();
        try {
            respBody.setData(USER_DAO.findByEmail(email));
            if (respBody.getData().size() == 0) {
                return new ResponseModel<>(ApiStatus.NO_CONTENT);
            }
            return respBody;
        } catch (NotValidException e) {
            return new ResponseModel<>(ApiStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseModel<>(ApiStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
