package com.cate.catenet.service;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;

public interface UserService {
    User create(User user) throws ApiResponseException;
}
