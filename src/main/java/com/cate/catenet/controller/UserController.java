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

    @PostMapping(value = "/solicitations")
    @ApiOperation(value = "Busca solicitações", notes = "Por meio desse serviço será possivel buscar os usuarios que estão solicitando acesso (Status: ENTRY_ACCESS)")
    public ResponseEntity registerTempUser(@RequestBody User user) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            User userResponse = userService.registerTempUser(user);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Usuario criado com sucesso");
            apiResponseDTO.setObject(userResponse);

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch (ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }

    @DeleteMapping(value = "/blockUser/{id}")
    @ApiOperation(value = "Bloquear usuario", notes = "Por meio desse serviço será possivel bloquear o usuario informado")
    public ResponseEntity blockUser(@PathVariable Long id) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            userService.blockUser(id);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Usuario bloqueado com sucesso");

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch (ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }

    @GetMapping(value = "/solicitations")
    @ApiOperation(value = "Busca solicitações", notes = "Por meio desse serviço será possivel buscar os usuarios que estão solicitando acesso (Status: ENTRY_ACCESS)")
    public ResponseEntity getSolicitations() {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            List<User> userList = userService.getSolicitations();

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Usuario bloqueado com sucesso");
            apiResponseDTO.setObject(userList);

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch (ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }

    @GetMapping(value = "/aprove/{id}")
    @ApiOperation(value = "Aprovar usuario", notes = "Por meio desse serviço será possivel aprovar o usuario informado")
    public ResponseEntity aproveUser(@PathVariable Long id) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();

        try {
            userService.aproveUser(id);

            apiResponseDTO.setStatus(HttpStatus.OK);
            apiResponseDTO.setMessage("Usuario aprovado com sucesso");

            return ResponseEntity.status(apiResponseDTO.getStatus()).body(apiResponseDTO);
        } catch (ApiResponseException e) {
            apiResponseDTO.setStatus(e.getStatus());
            apiResponseDTO.setMessage(e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(apiResponseDTO);
        }
    }

}
