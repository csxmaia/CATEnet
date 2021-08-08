package com.cate.catenet.service;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) throws ApiResponseException {
        try {
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiResponseException("Erro ao realizar cadastro de usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
