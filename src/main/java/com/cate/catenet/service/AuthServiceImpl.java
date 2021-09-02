package com.cate.catenet.service;

import com.cate.catenet.enums.StatusUserEnum;
import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User signUp(User user) throws ApiResponseException {
        try {
            user.setStatus(StatusUserEnum.ENTRY_ACCESS.name());
            String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(7));
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiResponseException("Erro ao realizar cadastro de usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User login(User userVO) throws ApiResponseException {
        try {

            Optional<User> user = userRepository.findByEmail(userVO.getEmail());

            if (!user.isPresent()) {
                throw new ApiResponseException("Email não encontrado.", HttpStatus.NOT_FOUND);
            }

            boolean matches = BCrypt.checkpw(userVO.getPassword(), user.get().getPassword());
            if (!matches) {
                throw new ApiResponseException("Senha incorreta.", HttpStatus.UNAUTHORIZED);
            }

            return user.get();
        } catch (ApiResponseException e) {
            throw new ApiResponseException(e.getMessage(), e.getStatus());
        } catch (Exception e) {
            throw new ApiResponseException("Erro ao realizar login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
