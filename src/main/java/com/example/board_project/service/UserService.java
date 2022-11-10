package com.example.board_project.service;

import com.example.board_project.domain.User;
import com.example.board_project.dto.CreateUserDTO;
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

        //CQRS 분리

        //Command
        User newUser = new User();
        newUser.createUser(createUserDTO);
        userRepository.save(newUser);

        //Query
        User checkUser = userRepository.findById(newUser.getId()).orElse(null);

        return checkUser;

    }
}
