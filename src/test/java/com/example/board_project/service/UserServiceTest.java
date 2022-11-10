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
        assertEquals(newUser, userRepository.findByNicknameAndEmail(newUser.getNickname(), newUser.getEmail()));

    }

    @Test
    void 회원가입_중복() {

        // given
        CreateUserDTO createUserDTO1 = new CreateUserDTO();
        createUserDTO1.setEmail("1217smj@naver.com");
        createUserDTO1.setNickname("sachena");
        createUserDTO1.setPassword("1234");
        User newUser1 = userService.join(createUserDTO1);



        // when
        CreateUserDTO createUserDTO2 = new CreateUserDTO();
        createUserDTO2.setEmail("1217smj@naver.com");
        createUserDTO2.setNickname("sachena");
        createUserDTO2.setPassword("1234");


        // then
        assertThrows(DuplicateException.class, ()->{
            userService.join(createUserDTO2);
        }
        );

    }


}