package com.example.board_project.service;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
import com.example.board_project.dto.DeleteUserDto;
import com.example.board_project.dto.EditUserDTO;
import com.example.board_project.dto.RetrieveUserDto;
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
        User editUser = userRepository.findByEmail(editUserDTO.getEmail());
        if(editUser == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }

        if(userRepository.existsByNickname( editUserDTO.getNickname())){
            throw new DuplicateException("중복된 닉네임 입니다.");
        }


        //user 정보 수정 (변경감지 사용)
        editUser.editUser(editUserDTO);

        return editUser;

    }

    @Transactional
    public User deleteUser(DeleteUserDto deleteUserDto) {

        //Check Value
        User deleteUser = userRepository.findByEmail(deleteUserDto.getEmail());
        if(deleteUser == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }

        //user 삭제 (변경감지 사용)
        deleteUser.deleteUser(deleteUserDto);

        return deleteUser;

    }

    public User retrieveUser(RetrieveUserDto retrieveUserDto) {

        //Check Value
        User retrieveUser = userRepository.findByEmail(retrieveUserDto.getEmail());
        if(retrieveUser == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }
        return retrieveUser;
    }
}
