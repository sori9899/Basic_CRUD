package com.example.CRUD.service;

import com.example.CRUD.entity.Post;
import com.example.CRUD.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public String PostSave(Post post){
        try{
            postRepository.save(post);
        }catch (Exception e){
            return e.getMessage();
        }
        return "save success";
    }


    public String PostDeleted(Long id){
        try{
            if(postRepository.findById(id).isEmpty()){
                return "error / It's empty ";
            }
            postRepository.deleteById(id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "deleted success";
    }

    public List<Post> PostReturn(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC , "created_at"));
    }

    public Post PostView(Long id){
        return postRepository.findById(id).get();
    }


    public List<Post> PostFindMatch(String string){
        return postRepository.findAllByContentContainingOrTitleContaining(string, string);
    }


}
