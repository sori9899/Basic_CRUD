package com.example.CRUD.controller;

import ch.qos.logback.core.model.Model;
import com.example.CRUD.entity.Post;
import com.example.CRUD.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/save")
    public String RegisterPost (@RequestBody Post post, Model model){
        System.out.println("d");
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        post.setCreated_at(offsetDateTime);

        System.out.println(post);

        return postService.PostSave(post);
    }

    @PostMapping("/delete")
    public String ErasePost (Long id){
        return postService.PostDeleted(id);
    }

    @GetMapping("/list")
    public List<Post> GetAllPost(){
        return postService.PostReturn();
    }

    @PostMapping("/update")
    public String UpdatePost(@RequestBody Post post, Model model){
        Post posting = postService.PostView(post.getId());
        
        posting.setTitle(post.getTitle());
        posting.setContent(post.getContent());

        return postService.PostSave(posting);
    }

    @GetMapping("/search")
    public List<Post> ConditionSearchPost(String string){
        System.out.println(string);
        return postService.PostFindMatch(string);
    }

}
