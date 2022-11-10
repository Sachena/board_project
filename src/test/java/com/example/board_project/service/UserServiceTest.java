package com.example.board_project.service;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
import com.example.board_project.exception.DuplicateException;
import com.example.board_project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.DigestException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 회원가입() {

        // given
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("1217smj@naver.com");
        createUserDTO.setNickname("sachena");
        createUserDTO.setPassword("1234");

        User testUser = new User();
        testUser.createUser(createUserDTO);


        // when
        User newUser = userService.join(createUserDTO);

        // then
        assertEquals(newUser, userRepository.findByNickname(newUser.getNickname()));

    }

    @Test
    void 회원가입_중복() {

        // given
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("1217smj@naver.com");
        createUserDTO.setNickname("sachena");
        createUserDTO.setPassword("1234");

        User testUser = new User();
        testUser.createUser(createUserDTO);


        // when
        User newUser1 = userService.join(createUserDTO);

        // then
        assertThrows(DuplicateException.class, ()->{
            userService.join(createUserDTO);
        }
        );

    }


}