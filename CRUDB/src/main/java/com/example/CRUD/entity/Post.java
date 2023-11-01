package com.example.CRUD.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Long writter;
    private OffsetDateTime created_at;
    private OffsetDateTime deleted_at;
}
