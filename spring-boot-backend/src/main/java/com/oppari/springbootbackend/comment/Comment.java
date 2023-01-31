package com.oppari.springbootbackend.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oppari.springbootbackend.post.Post;
import com.oppari.springbootbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime posted_at;
    @JsonIgnore
    @ManyToOne
    private Post post;

    public Comment(String content) {
        this.content = content;
    }
}
