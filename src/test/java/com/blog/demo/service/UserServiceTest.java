package com.blog.demo.service;

import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import com.blog.demo.service.Impl.UserServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private User USER = createUser();
    private UserServiceImp userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserServiceImp(userRepository);
    }

    @Test
    public void addUserTest(){

        when(userRepository.save(USER)).thenReturn(USER);

    }

    public User createUser(){
        User user = new User("u1", "username1", "u1@mail.com", "psw1");
        return user;
    }
}
