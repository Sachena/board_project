package com.example.board_project.controller;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
import com.example.board_project.dto.DeleteUserDto;
import com.example.board_project.dto.EditUserDTO;
import com.example.board_project.dto.RetrieveUserDto;
import com.example.board_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user")
    public User retrieveUser(@RequestBody @Valid RetrieveUserDto retrieveUserDto){
        return userService.retrieveUser(retrieveUserDto);

    }

    @PostMapping("/api/user")
    public User join(@RequestBody @Valid CreateUserDTO createUserDTO){

        //회원가입 로직
        return userService.join(createUserDTO);

    }

    @PutMapping("/api/user")
    public User editUser(@RequestBody @Valid EditUserDTO editUserDTO){

        //회원수정 로직
        return userService.editUser(editUserDTO);

    }

    @PutMapping("/api/deleteUser")
    public User deleteUser(@RequestBody @Valid DeleteUserDto deleteUserDto){

        //회원탈퇴 로직
        return userService.deleteUser(deleteUserDto);

    }



}
