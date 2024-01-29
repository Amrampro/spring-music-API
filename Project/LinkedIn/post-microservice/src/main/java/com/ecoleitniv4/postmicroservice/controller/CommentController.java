package com.ecoleitniv4.postmicroservice.controller;

import com.ecoleitniv4.postmicroservice.dto.CommentRequest;
import com.ecoleitniv4.postmicroservice.dto.CommentResponse;
import com.ecoleitniv4.postmicroservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
// Documenting using swagger
@Tag(
        name = "CRUD REST APIs for comments",
        description = "The CRUD API for CREATE, READ, UPDATE and DELETE comments"
)
public class CommentController {
    public final CommentService commentService;

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Swagger documentation
    @Operation(
            summary = "Create a comment on a post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    public void createComment(@RequestBody CommentRequest commentRequest){ commentService.createComment(commentRequest);}

    // Read - all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch all comments of posts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public List<CommentResponse> readAllComments(){ return commentService.readAllComments(); }

    // Read - single
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch a single comment"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public CommentResponse readSingleComment(@PathVariable Long id) { return commentService.readSingleComment(id); }

    // Update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Update a comment"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void updateComment(@PathVariable Long id, @RequestBody CommentRequest updateCommentRequest){
        commentService.updateComment(id, updateCommentRequest);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Delete a comment"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void deleteComment(@PathVariable Long id) { commentService.deleteComment(id); }
}
