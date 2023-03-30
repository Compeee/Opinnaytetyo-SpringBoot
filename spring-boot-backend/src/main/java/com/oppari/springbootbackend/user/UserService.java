package com.oppari.springbootbackend.user;

import com.oppari.springbootbackend.exception.InvalidPasswordException;
import com.oppari.springbootbackend.exception.UserNotFoundException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Validator validator;
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new UserNotFoundException("User not found with id: ", userId);
        }
        return userRepository.findById(userId);
    }

    public void changePassword(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with id: ", userId)
        );
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new UserNotFoundException("User not found with id: ", userId);
        }
        userRepository.deleteById(userId);
    }
}
