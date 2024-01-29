package com.ecoleittp.userservice.controller;

import com.ecoleittp.userservice.dto.UserRequest;
import com.ecoleittp.userservice.dto.UserResponse;
import com.ecoleittp.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

// Documenting using swagger
@Tag(
        name = "CRUD REST APIs for users",
        description = "The CRUD API for CREATE, READ, UPDATE and DELETE users"
)
public class UserController {

    public final UserService userService;

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Swagger documentation
    @Operation(
            summary = "Create a user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    public String createUser(@RequestBody UserRequest userRequest){ return userService.createUser(userRequest); }

    // ReadAll
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch all users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public List<UserResponse> readUsers(){ return userService.readUsers(); }

    // ReadSingle
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch a single user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public UserResponse readUser(@PathVariable Long userId){ return userService.readUser(userId);}

    // Put
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Update a user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public String updateUser(@PathVariable Long userId, @RequestBody UserRequest updatedUserRequest) {
        return userService.updateUser(userId, updatedUserRequest);
    }

    // Delete
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Delete a user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void deleteUser(@PathVariable Long userId){ userService.deleteUser(userId); }

    /***************************************************
     *               Other functions                   *
     ***************************************************/

    // authenticate
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Authentication of a user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public String authenticateUser(@RequestBody UserRequest userRequest) {
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();
        return userService.authenticateUser(username, password);
    }
}
