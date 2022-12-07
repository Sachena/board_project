package com.example.board_project.service;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
import com.example.board_project.dto.DeleteUserDto;
import com.example.board_project.dto.EditUserDTO;
import com.example.board_project.exception.DuplicateException;
import com.example.board_project.exception.InvalidUserException;
import com.example.board_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User join(CreateUserDTO createUserDTO) {


        //Check Value
        User checkEmailUser = userRepository.findByEmail( createUserDTO.getEmail());
        if(checkEmailUser != null){
            throw new DuplicateException("이미 가입된 회원입니다.");
        }


        if(userRepository.existsByNickname( createUserDTO.getNickname())){
            throw new DuplicateException("중복된 닉네임 입니다.");
        }


        //User 저장
        User newUser = new User();
        newUser.createUser(createUserDTO);
        userRepository.save(newUser);

        return newUser;

    }

    @Transactional
    public User editUser(EditUserDTO editUserDTO) {

        //Check Value
        User checkUser = userRepository.findByEmail(editUserDTO.getEmail());
        if(checkUser == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }

        if(userRepository.existsByNickname( editUserDTO.getNickname())){
            throw new DuplicateException("중복된 닉네임 입니다.");
        }


        //user 정보 수정 (변경감지 사용)
        checkUser.editUser(editUserDTO);

        return checkUser;

    }

    @Transactional
    public User deleteUser(DeleteUserDto deleteUserDto) {

        //Check Value
        User checkUser = userRepository.findByEmail(deleteUserDto.getEmail());
        if(checkUser == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }

        //user 삭제 (변경감지 사용)
        checkUser.deleteUser(deleteUserDto);

        return checkUser;

    }
}
