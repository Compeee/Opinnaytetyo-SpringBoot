package com.oppari.springbootbackend.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }
    @PostMapping
    public Comment createPost(@RequestBody Comment comment){
        return commentRepository.save(comment);
    }
}
