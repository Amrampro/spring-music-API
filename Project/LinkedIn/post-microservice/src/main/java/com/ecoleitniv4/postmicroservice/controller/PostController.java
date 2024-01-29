package com.ecoleitniv4.postmicroservice.controller;

import com.ecoleitniv4.postmicroservice.dto.PostRequest;
import com.ecoleitniv4.postmicroservice.dto.PostResponse;
import com.ecoleitniv4.postmicroservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
// Documenting using swagger
@Tag(
        name = "CRUD REST APIs for posts",
        description = "The CRUD API for CREATE, READ, UPDATE and DELETE posts"
)
public class PostController {
    public final PostService postService;

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Swagger documentation
    @Operation(
            summary = "Create a post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    public void createPost(@RequestBody PostRequest postRequest) { postService.createPost(postRequest); }

    // Read - all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch all posts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public List<PostResponse> readAllPosts() { return postService.readAllPosts(); }

    // Read - single
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch a single post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public PostResponse readSinglePost(@PathVariable Long id) { return postService.readSinglePost(id); }

    // Update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Update a post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void updatePost(@PathVariable Long id, @RequestBody PostRequest updatePostRequest) {
        postService.updatePost(id, updatePostRequest);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Delete a post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void deletePost(@PathVariable Long id) { postService.deletePost(id);}
}
