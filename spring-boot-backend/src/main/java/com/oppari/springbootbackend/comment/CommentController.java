package com.oppari.springbootbackend.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "Get a list of all the comments", description = "Requires you to be authenticated")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public List<Comment> getComments(){
        return commentService.getComments();
    }
    @Operation(summary = "Create a new comment", description = "Requires you to be authenticated")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping(path="/{postId}")
    public Comment createComment(@RequestBody Comment comment, @PathVariable Long postId){
        return commentService.createComment(comment, postId);
    }
    @Operation(summary = "Get all comments for a post", description = "Requires you to be authenticated")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId){
        return commentService.getCommentsForPost(postId);
    }
    @Operation(summary = "Delete a comment", description = "Users can only delete their own comments, admins can delete any")
    @PreAuthorize("#userId == principal.id OR hasAuthority('ADMIN')")
    @DeleteMapping("/{commentId}")
    public void deleteComment(@RequestParam Long userId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
    @Operation(summary = "Edit a comment", description = "Users can edit their own commends, admin can edit any")
    @PreAuthorize("#editCommentRequest.userId == principal.id OR hasAuthority('ADMIN')")
    @PatchMapping("/{commentId}")
    public void editComment(@PathVariable Long commentId, @RequestBody EditCommentRequest editCommentRequest){
        commentService.editComment(commentId, editCommentRequest.getContent());
    }
}
