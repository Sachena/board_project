package com.example.board_project.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<AttachedFile> attachedFiles = new ArrayList<>();

    public void addAttachedFile(AttachedFile attachedFile){
        this.attachedFiles.add(attachedFile);
    }


    public void createPost(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
