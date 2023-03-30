package com.oppari.springbootbackend.user;

import com.oppari.springbootbackend.exception.InvalidPasswordException;
import com.sun.tools.jconsole.JConsoleContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Validated
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get a list of all registered users", description = "This endpoint is for admins only")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @Operation(summary = "Get an user by id", description = "You can only get ur own information as an user, admins can find anyones information")
    @PreAuthorize("#userId == principal.id OR hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

    @Operation(summary = "Delete an user", description = "Users can only delete their own accounts, admin can delete anyones")
    @PreAuthorize("#userId == principal.id OR hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @Operation(summary = "Change password", description = "Users can change their own passwords")
    @PreAuthorize("#userId == principal.id")
    @PatchMapping(path = "/{userId}")
    public void changeUserPassword(@PathVariable("userId") Long userId, @RequestBody ChangePassRequest changePassRequest){
        if (changePassRequest.getPassword() == null || changePassRequest.getPassword().length() < 6) {
            throw new InvalidPasswordException("Password must not be empty and must be at least 6 characters long");
        }
        userService.changePassword(userId, changePassRequest.getPassword());
    }
}
