package com.ecoleitniv4.profilemicroservice.controller;

import com.ecoleitniv4.profilemicroservice.dto.ProfileRequest;
import com.ecoleitniv4.profilemicroservice.dto.ProfileResponse;
import com.ecoleitniv4.profilemicroservice.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor

// Documenting using swagger
@Tag(
        name = "CRUD REST APIs for profiles",
        description = "The CRUD API for CREATE, READ, UPDATE and DELETE profiles"
)
public class ProfileController {
    public final ProfileService profileService;

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Swagger documentation
    @Operation(
            summary = "Create a profile"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    public void createProfile(@RequestBody ProfileRequest profileRequest){ profileService.createProfile(profileRequest); }

    // Read - All
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch all profiles"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public List<ProfileResponse> readProfiles(){ return profileService.readAllProfiles(); }

    // Read - Single
    @GetMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch a single profile"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public ProfileResponse readProfile(@PathVariable Long profileId){ return profileService.readProfile(profileId); }

    // Update
    @PutMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Update a profile"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void updateProfile(@PathVariable Long profileId, @RequestBody ProfileRequest updateProfileRequest){
        profileService.updateProfile(profileId, updateProfileRequest);
    }

    // Delete
    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Delete a profile"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void deleteProfile(@PathVariable Long profileId) { profileService.deleteProfile(profileId);}
}
