package com.cate.catenet.service;

import com.cate.catenet.enums.StatusUserEnum;
import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User userVO) throws ApiResponseException {
        try {
            userVO.setStatus(StatusUserEnum.ENTRY_ACCESS.name());
            userRepository.save(userVO);
            return userVO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiResponseException("Erro ao realizar cadastro de usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<User> get(String statusEnum, Long limit, Long page) throws ApiResponseException {
        try {
            Optional<List<User>> user = userRepository.findByStatus(statusEnum);

            if(user.isEmpty()) {
                throw new ApiResponseException("Nenhum usuario encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return user.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiResponseException("Erro ao realizar cadastro de usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
