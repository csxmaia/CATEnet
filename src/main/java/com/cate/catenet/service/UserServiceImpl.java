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
import java.util.Locale;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> get(String statusEnum) throws ApiResponseException {
        try {
            Optional<List<User>> user = userRepository.findByStatus(statusEnum.toUpperCase());

            if(user.isEmpty()) {
                throw new ApiResponseException("Nenhum usuario encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return user.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiResponseException("Erro ao realizar busca de usuarios", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User blockUser(Long id) throws ApiResponseException {
        try {
            Optional<User> user = userRepository.findById(id);

            if(user.isEmpty()) {
                throw new ApiResponseException("Nenhum usuario encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            user.get().setStatus(StatusUserEnum.BLOCKED.name());

            userRepository.save(user.get());

            return user.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiResponseException("Erro ao bloquear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
