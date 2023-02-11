package com.oppari.springbootbackend.post;

import com.oppari.springbootbackend.comment.Comment;
import com.oppari.springbootbackend.user.User;
import com.oppari.springbootbackend.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PostController {

    private final PostService postService;
    @Operation(summary = "Get a list of all the posts", description = "Requires you to be authenticated")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public List<Post> getPosts(){
        return postService.getAllPosts();
    }
    @Operation(summary = "Create a new post", description = "Requires you to be authenticated")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER') AND #userId == principal.id")
    @PostMapping("/{userId}")
    public Post createPost(@RequestBody Post post, @PathVariable("userId") Long userId){
        return postService.createPost(post, userId);
    }
    @Operation(summary = "Delete a post", description = "Users can only delete their own posts, admin can delete any")
    @PreAuthorize("#userId == principal.id OR hasAuthority('ADMIN')")
    @DeleteMapping("/{postId}")
    public void deletePost(@RequestParam Long userId, @PathVariable("postId") Long postId){
        postService.deletePost(postId);
    }
    @Operation(summary = "Get all the posts by a chosen user", description = "Users can find their own posts, admins can find any")
    @PreAuthorize("#userId == principal.id OR hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public List<Post> getPostsByUserId(@PathVariable("userId") Long userId){
        return postService.getPostsByUser(userId);
    }
    @Operation(summary = "Edit the contents of a post", description = "Users can edit their own posts, admins can edit any")
    @PreAuthorize("#editPostRequest.userId == principal.id OR hasAuthority('ADMIN')")
    @PatchMapping("/{postId}")
    public void editPost(@PathVariable("postId") Long postId, @RequestBody EditPostRequest editPostRequest){
        postService.editPost(postId, editPostRequest.getContent());
    }
}

