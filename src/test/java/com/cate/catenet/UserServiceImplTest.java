package com.cate.catenet;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.service.AuthServiceImpl;
import com.cate.catenet.service.UserServiceImpl;
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
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void registerTempUser() throws ApiResponseException {
        User user = new User();
        user.setEmail("unit@tempuser.com");
        user.setName("tempuser");
        user.setPassword("tempuser123");
        user.setDescription("Justificativa da crição e outros comentarios");
        User userResponse = userService.registerTempUser(user);
        Assert.assertEquals("tempuser", userResponse.getName());
        Assert.assertEquals("unit@tempuser.com", userResponse.getEmail());
        Assert.assertEquals("ACTIVE", userResponse.getStatus());
        Assert.assertTrue(!userResponse.getDescription().equals(""));
        Assert.assertFalse(userResponse.getDescription() == null);
    }

}
