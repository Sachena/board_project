package com.example.board_project.service;


import com.example.board_project.domain.AttachedFile;
import com.example.board_project.domain.Post;
import com.example.board_project.domain.User;
import com.example.board_project.dto.CreatePostDto;
import com.example.board_project.exception.DuplicateException;
import com.example.board_project.exception.InvalidUserException;
import com.example.board_project.repository.PostRepository;
import com.example.board_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public Post createPost(CreatePostDto createPostDto) {

        //Check Value
        User author = userRepository.findByNickname(createPostDto.getNickname());
        if(author == null){
            throw new InvalidUserException("올바르지 않은 사용자입니다.");
        }

        //Post 생성
        Post newPost = new Post();
        newPost.createPost(createPostDto.getTitle(), createPostDto.getDescription(), author);

        // 생성된 Post에 attachFile연관관계 생성
        for( String fileUrl : createPostDto.getFileUrls()){
            AttachedFile newAttachedFile = new AttachedFile();
            newAttachedFile.createAttachedFile(fileUrl, newPost);
            newPost.addAttachedFile(newAttachedFile);
        }

        postRepository.save(newPost);

        return newPost;


    }
}
