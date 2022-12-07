package com.example.board_project.service;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
import com.example.board_project.dto.DeleteUserDto;
import com.example.board_project.dto.EditUserDTO;
import com.example.board_project.exception.DuplicateException;
import com.example.board_project.exception.InvalidUserException;
import com.example.board_project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.security.DigestException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

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
        assertEquals(newUser, userRepository.findByEmail(newUser.getEmail()));

    }

    @Test
    void 회원가입_이메일중복() {

        // given
        CreateUserDTO createUserDTO1 = new CreateUserDTO();
        createUserDTO1.setEmail("1217smj@naver.com");
        createUserDTO1.setNickname("sachena");
        createUserDTO1.setPassword("1234");
        User newUser1 = userService.join(createUserDTO1);



        // when
        CreateUserDTO createUserDTO2 = new CreateUserDTO();
        createUserDTO2.setEmail("1217smj@naver.com");
        createUserDTO2.setNickname("sachena11");
        createUserDTO2.setPassword("1234");


        // then
        assertThrows(DuplicateException.class, ()->{
            userService.join(createUserDTO2);
        }
        );

    }

    @Test
    void 회원가입_닉네임중복() {

        // given
        CreateUserDTO createUserDTO1 = new CreateUserDTO();
        createUserDTO1.setEmail("1217smj@naver.com");
        createUserDTO1.setNickname("sachena");
        createUserDTO1.setPassword("1234");
        User newUser1 = userService.join(createUserDTO1);



        // when
        CreateUserDTO createUserDTO2 = new CreateUserDTO();
        createUserDTO2.setEmail("awssag@naver.com");
        createUserDTO2.setNickname("sachena");
        createUserDTO2.setPassword("1234");


        // then
        assertThrows(DuplicateException.class, ()->{
                    userService.join(createUserDTO2);
                }
        );

    }


    @Test
    void 회원정보수정() {

        // given
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setEmail("test@naver.com");
        editUserDTO.setNickname("edit");



        // when
        User editUser = userRepository.findByEmail(editUserDTO.getEmail());
        editUser.editUser(editUserDTO);


        // then
        assertEquals("edit", editUser.getNickname());

    }

    @Test
    void 회원정보수정_이메일오류() {

        // given
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setEmail("asd@naver.com");
        editUserDTO.setNickname("test");



        // when


        // then
        assertThrows(InvalidUserException.class, ()->{
                    userService.editUser(editUserDTO);
                }
        );

    }

    @Test
    void 회원정보수정_닉네임중복() {

        // given
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setEmail("test@naver.com");
        editUserDTO.setNickname("test");



        // when


        // then
        assertThrows(DuplicateException.class, ()->{
                    userService.editUser(editUserDTO);
                }
        );

    }

    @Test
    void 회원탈퇴() {

        // given
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("deleteTest@naver.com");
        createUserDTO.setNickname("deleteTest");
        createUserDTO.setPassword("1234");

        User testUser =  userService.join(createUserDTO);


        // when
        DeleteUserDto deleteUserDto = new DeleteUserDto();
        deleteUserDto.setEmail(testUser.getEmail());
        userService.deleteUser(deleteUserDto);


        // then
        assertEquals(testUser.getIsDeleted(), true);

    }

    @Test
    void 회원탈퇴_유효하지않은이메일() {

        // given
        DeleteUserDto deleteUserDto = new DeleteUserDto();
        deleteUserDto.setEmail("asdasd");



        // when



        // then
        assertThrows(InvalidUserException.class, ()->{
                    userService.deleteUser(deleteUserDto);
                }
        );

    }



}