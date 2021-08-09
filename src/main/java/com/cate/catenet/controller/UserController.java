package com.cate.catenet.controller;

import com.cate.catenet.dto.ApiResponseDTO;
import com.cate.catenet.exception.ApiResponseException;
import com.cate.catenet.model.User;
import com.cate.catenet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@Api(value = "Api Users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro do usuario", notes = "Permite o auto registro do usuario, registrando-o como status inicial entry_access.")
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

    @GetMapping()
    @ApiOperation(value = "Busca todos os usuarios", notes = "Por meio desse serviço o usuario terá acesso aos usuarios registrados no banco de dados")
    public ResponseEntity get(
            @RequestParam(value = "userStatus", required = true) String statusUser,
            @RequestParam(value = "limit", required = false) Long limit,
            @RequestParam(value = "page", required = false) Long page
    ) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            List<User> user = userService.get(statusUser, limit, page);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Usuario criado com sucesso");
            //remover password
            apiResponseDTO.setObject(user);

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch (ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }

}
