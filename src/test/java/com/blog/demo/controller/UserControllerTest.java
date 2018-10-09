package com.blog.demo.controller;

import com.blog.demo.model.User;
import com.blog.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    Logger LOG = LoggerFactory.getLogger(UserControllerTest.class);

    private User USER = createUser();

    private ObjectMapper objectMapper;
    private UserController userController;

    @Mock
    private UserService mockUserService;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.userController = new UserController(mockUserService);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void postUser() throws Exception {
        String packageJson = objectMapper.writeValueAsString(USER);

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(packageJson))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    public User createUser(){
        User user = new User("u1", "username1", "u1@mail.com", "psw1");
        return user;
    }

}


    /*

    mockMvc.perform(post("/add/package")
                .contentType(MediaType.APPLICATION_JSON)
                .content(packageJson))
                .andExpect(status().isCreated())
                .andDo(print());

       @Test
    public void putCustomShortId() throws Exception {
        String customId = "jobs";
        mvc.perform(put("/shorturl/" + customId).param("longUrl",
                "http://www.daimler.com/karriere/berufserfahrene/direkteinstieg/")).andExpect(status().isOk());
    }
     */