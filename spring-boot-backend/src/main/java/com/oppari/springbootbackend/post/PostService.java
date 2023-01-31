package com.oppari.springbootbackend.post;

import com.oppari.springbootbackend.user.User;
import com.oppari.springbootbackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public Post createPost(Post post, Long userId){
        post.setPosted_at(LocalDateTime.now());
        User creatorOfPost = userRepository.findById(userId).orElseThrow();
        post.setUser(creatorOfPost);
        List<Post> posts = creatorOfPost.getPosts();
        posts.add(post);
        creatorOfPost.setPosts(posts);
        return postRepository.save(post);
    }

    public List<Post> getPostsByUser(Long userId){
        return postRepository.findByUserId(userId);
    }
    public void deletePost(Long postId) {
        if(!postRepository.existsById(postId)){
            throw new IllegalStateException("No post with id" + postId + "exists");
        }
        postRepository.deleteById(postId);
    }

    public void editPost(Long postId, String content) {
        if(!postRepository.existsById(postId)){
            throw new IllegalStateException("No post with id" + postId + "exists");
        }
        Post edited_post = postRepository.findById(postId).orElseThrow();
        edited_post.setContent(content);
        postRepository.save(edited_post);
    }
}
