package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static com.example.demo.TestUtil.readJsonFile;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerImplTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    @DisplayName("Autentica com sucesso")
    void authenticate(){
        String body = readJsonFile("login-valid.json");
        MockHttpServletRequestBuilder requestBuilder = post("/login")
                .contentType(APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DisplayName("lanca erro ao tentar autenticar sem password")
    void authenticate2(){
        String body = readJsonFile("login-no-password.json");
        MockHttpServletRequestBuilder requestBuilder = post("/login")
                .contentType(APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }

    @Test
    @SneakyThrows
    @DisplayName("lanca erro ao tentar autenticar sem username")
    void authenticate3(){
        String body = readJsonFile("login-no-username.json");
        MockHttpServletRequestBuilder requestBuilder = post("/login")
                .contentType(APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }
}