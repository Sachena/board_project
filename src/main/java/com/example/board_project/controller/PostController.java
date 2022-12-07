package com.example.board_project.controller;


import com.example.board_project.domain.Post;
import com.example.board_project.dto.CreatePostDto;
import com.example.board_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @PostMapping("/api/post")
    public Post createPost(CreatePostDto createPostDto){

        return postService.createPost(createPostDto);

    }




}
