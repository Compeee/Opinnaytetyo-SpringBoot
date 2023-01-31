package com.oppari.springbootbackend.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oppari.springbootbackend.comment.Comment;
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
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime posted_at;
    @JsonIgnore
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post(String content){
        this.content = content;
    }

}
