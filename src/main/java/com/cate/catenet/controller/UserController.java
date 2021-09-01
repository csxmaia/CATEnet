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

    @GetMapping()
    @ApiOperation(value = "Busca todos os usuarios", notes = "Por meio desse serviço o usuario terá acesso aos usuarios registrados no banco de dados")
    public ResponseEntity get(@RequestParam(value = "userStatus", required = true) String statusUser) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            List<User> user = userService.get(statusUser);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setObject(user);

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch (ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Bloquear usuario", notes = "Por meio desse serviço será possivel bloquear o usuario informado")
    public ResponseEntity blockUser(@PathVariable Long id) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            User user = userService.blockUser(id);

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
