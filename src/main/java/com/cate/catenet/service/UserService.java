package com.cate.catenet.service;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;

import java.util.List;

public interface UserService {
    List<User> get(String statusEnum) throws ApiResponseException;

    User signUp(User user) throws ApiResponseException;

    User blockUser(Long userId) throws ApiResponseException;
}
