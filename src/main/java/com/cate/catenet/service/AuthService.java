package com.cate.catenet.service;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;

public interface AuthService {

    User signUp(User user) throws ApiResponseException;

    User login(User user) throws ApiResponseException;

}
