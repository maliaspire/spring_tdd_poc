package com.mxninja.example.spring_tdd_poc.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mxninja.example.spring_tdd_poc.domain.UserDomain;
import com.mxninja.example.spring_tdd_poc.models.ApiStatus;
import com.mxninja.example.spring_tdd_poc.models.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserCommandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenSaveUserWithValidDataReturnCreatedWithData() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setFirstName("Slifer");
        userModel.setLastName("Sky Dragon");
        userModel.setAge(2000);
        userModel.setEmail(new ArrayList<>());
        userModel.getEmail().add("slifer14@yahoo.com");
        userModel.setContentNumber(new ArrayList<>());
        String jsonContent = new GsonBuilder().serializeNulls().create().toJson(userModel);
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(jsonPath("$.code", is(ApiStatus.CREATED.getCode())))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void whenSaveUserWithNullDataThenReturnBadRequest() throws Exception {
        UserModel userModel = new UserModel();
        String jsonContent = new GsonBuilder().serializeNulls().create().toJson(userModel);
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(jsonPath("$.code", is(ApiStatus.BAD_REQUEST.getCode())));
    }
}
