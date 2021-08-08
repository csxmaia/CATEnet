package com.cate.catenet.controller;

import com.cate.catenet.dto.ApiResponseDTO;
import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
@Api(value = "Api Users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(name = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody User user) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        try {
            userService.create(user);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Usuario criado com sucesso");
//            apiResponseDTO.setObject(createdUser);

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch(ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }

    }
}
