package com.example.Restfuljdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private RestfulJdbcApplication restfulJdbcApplication;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void setUp() {
        DataSetup ds = new DataSetup(jdbcTemplate);
        ds.createAndDeleteTable();
    }

    @Test
    void newCustomer() throws Exception {
        String s = "{\"firstName\":\"Austin\",\"lastName\":\"Noh\"}";
        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomer() throws Exception {
        String s = "{\"firstName\":\"Austin\",\"lastName\":\"Noh\"}";
        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s));
        this.mockMvc.perform(get("/customer/Austin")).andExpect(status().isOk());

    }

    @Test
    void deleteCustomer() throws Exception {
        String s = "{\"firstName\":\"Austin\",\"lastName\":\"Noh\"}";
        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s));
        this.mockMvc.perform(delete("/customer/1")).andExpect(status().isOk());
    }

    @Test
    void putCustomer() throws Exception {
        String s = "{\"firstName\":\"Austin\",\"lastName\":\"Noh\"}";
        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s));
        this.mockMvc.perform(put("/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
    }
    @Test
    void throwExceptionPost() throws Exception { // throws exception for bad name
        String s = "{\"firstName\":\"Austin1\",\"lastName\":\"Noh\"}";
        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isBadRequest());
    }

    @Test
    void throwExceptionGet() throws Exception { //missing last arg ("/customer/{firstName}")
        String s = "{\"firstName\":\"Austin\",\"lastName\":\"Noh\"}";

        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s));
        this.mockMvc.perform(get("/customer/")).andExpect(status().isBadRequest());

    }
    @Test
    void resourceNoFoundExceptionGet() throws Exception { // putting the wrong name
        String s = "{\"firstName\":\"Austin\",\"lastName\":\"Noh\"}";

        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s));
        this.mockMvc.perform(get("/customer/Austinn")).andExpect(status().isNotFound());
    }
    @Test
    void resourceNotFoundPut() throws Exception {
        String s = "{\"firstName\":\"Austin\",\"lastName\":\"Noh\"}"; // can use mock database to do tests
        setUp();
        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s));
        this.mockMvc.perform(put("/customer/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isNotFound());
    }
}