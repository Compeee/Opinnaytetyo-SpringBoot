package com.oppari.springbootbackend.user;

import com.oppari.springbootbackend.comment.Comment;
import com.oppari.springbootbackend.comment.CommentRepository;
import com.oppari.springbootbackend.post.Post;
import com.oppari.springbootbackend.post.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Configuration
public class UserConfig {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    CommandLineRunner commandLineLibraryUserRunner(UserRepository repository){
        return args -> {
            User user1 = new User("eero","eero@tuni.fi", bCryptPasswordEncoder.encode("password1"),UserRole.USER);
            User user2 = new User("jaska", "jaska@tuni.fi",bCryptPasswordEncoder.encode("password2"), UserRole.ADMIN);
            repository.saveAll(List.of(user1,user2));
        };
    }
}
