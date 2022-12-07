package com.example.board_project.service;


import com.example.board_project.domain.AttachedFile;
import com.example.board_project.domain.Post;
import com.example.board_project.domain.User;
import com.example.board_project.dto.CreatePostDto;
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
        User author = userRepository.findByNickname(createPostDto.getNickname());



        Post newPost = new Post();
        newPost.createPost(createPostDto.getTitle(), createPostDto.getDescription());

        for( String fileUrl : createPostDto.getFileUrls()){
            AttachedFile attachedFile = new AttachedFile();
            attachedFile.createAttachedFile(fileUrl, newPost);
            newPost.addAttachedFile(attachedFile);
        }

        return newPost;


    }
}
