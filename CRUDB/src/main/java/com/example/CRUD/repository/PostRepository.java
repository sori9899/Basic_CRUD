package com.example.CRUD.repository;

import com.example.CRUD.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByContentContainingOrTitleContaining (String content, String title);
}
