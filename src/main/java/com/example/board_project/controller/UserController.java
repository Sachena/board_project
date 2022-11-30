package com.example.board_project.controller;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
import com.example.board_project.dto.EditUserDTO;
import com.example.board_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user")
    public User join(@RequestBody @Valid CreateUserDTO createUserDTO){

        //회원가입 로직
        return userService.join(createUserDTO);

    }

    @PutMapping("/api/user")
    public User editUser(@RequestBody @Valid EditUserDTO editUserDTO){

        //회원가입 로직
        return userService.editUser(editUserDTO);

    }



}
