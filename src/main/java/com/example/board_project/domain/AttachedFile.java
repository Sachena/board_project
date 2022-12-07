package com.example.board_project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class AttachedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private void addPost(Post post){
        this.post = post;
    }


    public void createAttachedFile(String fileUrl, Post newPost) {
        this.url = fileUrl;
        this.post = newPost;
    }
}
