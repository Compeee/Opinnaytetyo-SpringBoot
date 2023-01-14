package com.oppari.springbootbackend.authentication;

import com.oppari.springbootbackend.config.JwtService;
import com.oppari.springbootbackend.user.User;
import com.oppari.springbootbackend.user.UserRepository;
import com.oppari.springbootbackend.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .userRole(UserRole.USER)
                .build();
        userRepository.save(user);
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    public AuthenticationResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
