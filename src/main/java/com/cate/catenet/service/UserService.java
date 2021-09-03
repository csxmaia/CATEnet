package com.cate.catenet.service;

import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;

import java.util.List;

public interface UserService {
    List<User> get(String statusEnum) throws ApiResponseException;

    User registerTempUser(User user) throws ApiResponseException;

    boolean blockUser(Long userId) throws ApiResponseException;

    List<User> getSolicitations() throws ApiResponseException;

    boolean aproveUser(Long userId) throws ApiResponseException;

    boolean deleteUser(Long userId) throws ApiResponseException;
}
