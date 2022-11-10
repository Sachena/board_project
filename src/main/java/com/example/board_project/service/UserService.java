package com.example.board_project.service;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
import com.example.board_project.exception.DuplicateException;
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
        User checkUser = userRepository.findByNicknameAndEmail(createUserDTO.getNickname(), createUserDTO.getEmail());
        if(checkUser != null){
            throw new DuplicateException("중복된 닉네임입니다");
        }

        //CQRS 분리
        //Command
        User newUser = new User();
        newUser.createUser(createUserDTO);
        userRepository.save(newUser);

        //Query => newUser 로 바로 리턴 vs 한번더 호출...?
        User callUser = userRepository.findById(newUser.getId()).orElse(null);

        return callUser;

    }

}
