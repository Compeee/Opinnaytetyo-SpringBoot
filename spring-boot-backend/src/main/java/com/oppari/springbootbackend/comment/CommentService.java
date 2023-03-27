package com.oppari.springbootbackend.comment;

import com.oppari.springbootbackend.exception.CommentNotFoundException;
import com.oppari.springbootbackend.exception.PostNotFoundException;
import com.oppari.springbootbackend.post.Post;
import com.oppari.springbootbackend.post.PostRepository;
import com.oppari.springbootbackend.user.User;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }
    public Comment createComment(Comment comment, Long postId){
        comment.setPosted_at(LocalDateTime.now());
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: ", postId));
        comment.setPost(post);
        List<Comment> comments = List.of(comment);
        post.setComments(comments);
        return commentRepository.save(comment);
    }
    public List<Comment> getCommentsForPost(Long postId){
        return commentRepository.findByPostId(postId);
    }
    public void deleteComment(Long commentId){
        if(!commentRepository.existsById(commentId)){
            throw new CommentNotFoundException("Comment not found with id ", commentId);
        }
        commentRepository.deleteById(commentId);
    }

    public void editComment(Long commentId, String content) {
        if(!commentRepository.existsById(commentId)){
            throw new CommentNotFoundException("Comment not found with id: ", commentId);
        }
        Comment edited_comment = commentRepository.findById(commentId).orElseThrow();
        edited_comment.setContent(content);
        commentRepository.save(edited_comment);
    }
}
