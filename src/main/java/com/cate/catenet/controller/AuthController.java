package com.cate.catenet.controller;

import com.cate.catenet.dto.ApiResponseDTO;
import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@Api(value = "Api Auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro do usuario", notes = "Permite o auto registro do usuario, registrando-o como status inicial entry_access.")
    public ResponseEntity signUp(@RequestBody User user) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            User createdUser = authService.signUp(user);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Registrado com sucesso, aguarde aprovação");
            apiResponseDTO.setObject(createdUser);

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch(ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Login do usuario", notes = "Permite o login do usuario")
    public ResponseEntity login(@RequestBody User user) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        try {
            authService.login(user);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Login realizado com sucesso, aguarde aprovação");
            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch(ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }
}
