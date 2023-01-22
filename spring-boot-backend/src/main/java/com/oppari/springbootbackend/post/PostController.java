package com.oppari.springbootbackend.post;

import com.oppari.springbootbackend.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    @GetMapping
    public List<Post> getPosts(){
        return postRepository.findAll();
    }
    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }
}

