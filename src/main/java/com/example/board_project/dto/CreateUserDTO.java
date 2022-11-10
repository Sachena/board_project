package com.example.board_project.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateUserDTO {


    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

}
