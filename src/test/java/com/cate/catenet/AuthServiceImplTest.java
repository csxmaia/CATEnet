package com.cate.catenet;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.service.AuthServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AuthServiceImplTest {

    @Autowired
    private AuthServiceImpl authService;

    @Test
    public void signup() throws ApiResponseException {
        User user = new User();
        user.setEmail("unit@test.com");
        user.setName("test");
        user.setPassword("test123");
        User userResponse = authService.signUp(user);
        Assert.assertEquals("test", userResponse.getName());
        Assert.assertEquals("unit@test.com", userResponse.getEmail());
    }

    @Test
    public void login() throws ApiResponseException {
        User user = new User();
        user.setEmail("unit@test.com");
        user.setPassword("test123");
        User userResponse = authService.login(user);
        Assert.assertEquals("test", userResponse.getName());
        Assert.assertEquals("unit@test.com", userResponse.getEmail());
    }

    @Test
    public void passwordErrorLogin() {
        User user = new User();
        user.setEmail("unit@test.com");
        user.setPassword("999999999999999999999999999");
        try {
            User userResponse = authService.login(user);
        } catch (ApiResponseException e) {
            Assert.assertEquals("Senha incorreta.", e.getMessage());
        }
    }

    @Test
    public void userNotFoundLogin() {
        User user = new User();
        user.setEmail("unit@test999999999999.com");
        user.setPassword("test123");
        try {
            User userResponse = authService.login(user);
        } catch (ApiResponseException e) {
            Assert.assertEquals("Email não encontrado.", e.getMessage());
        }
    }

}
