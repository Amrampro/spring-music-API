package com.ecoleittp.userservice.controller;

import com.ecoleittp.userservice.dto.UserRequest;
import com.ecoleittp.userservice.dto.UserResponse;
import com.ecoleittp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api/users")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    //Creating a new User
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
        // return "User created successfully";
    }

    // Obtaining list of users
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    //Getting a single user
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getSingleUser(@PathVariable Long userId) { return userService.getSingleUser(userId);}

    // Deleting user
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) { userService.deleteUser(userId);}

    // Update user
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest userUpdateRequest) {

        UserResponse updatedUser = userService.updateUser(userId, userUpdateRequest);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
