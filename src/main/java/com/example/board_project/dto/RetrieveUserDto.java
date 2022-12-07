package com.example.board_project.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class RetrieveUserDto {

    @NotEmpty
    private String email;


}
