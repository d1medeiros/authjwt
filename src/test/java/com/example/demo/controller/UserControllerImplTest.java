package com.example.demo.controller;

import com.example.demo.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerImplTest {

    private String id = "1";

    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    void createUser() {
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.readJsonFile("create-user.json"));

        final ResultActions result = mvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void updateUser() {
        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/user", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil.readJsonFile("update-user.json"));

        final ResultActions result = mvc.perform(request)
                .andExpect(status().isCreated());
    }
}
