package com.cate.catenet.service;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;

import java.util.List;

public interface UserService {
    User create(User user) throws ApiResponseException;

    List<User> get(String statusUser, Long limit, Long page) throws ApiResponseException;
}
