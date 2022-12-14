package com.example.board_project.repository;

import com.example.board_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    boolean existsByNickname(String nickname);

    User findByNickname(String nickname);
}
