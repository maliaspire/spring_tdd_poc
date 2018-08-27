package com.mxninja.example.spring_tdd_poc.controllers;

import com.mxninja.example.spring_tdd_poc.models.ApiStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * 8/27/2018
 *
 * @author Mohammad Ali
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserQueryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenFindAllUsers() throws Exception {
        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(ApiStatus.OK.getCode())))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void whenFindByIdWithNotValidIdThenReturnBadRequest() throws Exception {
        mvc.perform(get("/users/id/randomString").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(ApiStatus.BAD_REQUEST.getCode())));
    }

    @Test
    public void whenFindByIdWithValidIdThenReturnOkWithData() throws Exception {
        mvc.perform(get("/users/id/20484d93-fe2b-4bd5-88f9-91ec4c459bfc"))
                .andExpect(jsonPath("$.code", is(ApiStatus.OK.getCode())))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void whenFindByEmailWithNotValidEmailReturnBadRequest() throws Exception {
        mvc.perform(get("/users/email/randomString"))
                .andExpect(jsonPath("$.code", is(ApiStatus.BAD_REQUEST.getCode())));
    }

    @Test
    public void whenFindByEmailWithValidEmailReturnNoContent() throws Exception {
        mvc.perform(get("/users/email/mxninja-@gmail.com"))
                .andExpect(jsonPath("$.code", is(ApiStatus.NO_CONTENT.getCode())))
                .andExpect(jsonPath("$.data", anything()));
    }

    @Test
    public void whenFindByEmailWithValidEmailReturnOkWithData() throws Exception {
        mvc.perform(get("/users/email/mxninja-@hotmail.com"))
                .andExpect(jsonPath("$.code", is(ApiStatus.OK.getCode())))
                .andExpect(jsonPath("$.data", anything()));
    }

}
