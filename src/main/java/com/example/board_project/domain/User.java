package com.example.board_project.domain;


import com.example.board_project.dto.CreateUserDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private LocalDateTime joinedAt;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    //setter업애기 위해?
    public void createUser(CreateUserDTO createUserDTO){
        this.email = createUserDTO.getEmail();
        this.password = createUserDTO.getPassword();
        this.nickname = createUserDTO.getNickname();

        this.joinedAt = LocalDateTime.now();


    }

}
