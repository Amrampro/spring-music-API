package com.ecoleitniv4.profilemicroservice.controller;

import com.ecoleitniv4.profilemicroservice.dto.ProfilesConnectionRequest;
import com.ecoleitniv4.profilemicroservice.dto.ProfilesConnectionResponse;
import com.ecoleitniv4.profilemicroservice.service.ProfilesConnectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile_connections")
@RequiredArgsConstructor

// Documenting using swagger
@Tag(
        name = "CRUD REST APIs for profiles Connections",
        description = "The CRUD API for CREATE, READ, UPDATE and DELETE Connections"
)
public class ProfilesConnectionController {
    public final ProfilesConnectionService profilesConnectionService;

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Swagger documentation
    @Operation(
            summary = "Create a connection"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    public void createProfileConnections(@RequestBody ProfilesConnectionRequest profilesConnectionRequest) { profilesConnectionService.createProfileSConnections(profilesConnectionRequest); }

    // Read - All
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch all Connections"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public List<ProfilesConnectionResponse> readProfilesConnections(){ return profilesConnectionService.readAllProfilesConnection(); }

    // Read - Single
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Read/Fetch a single connection"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public ProfilesConnectionResponse readSingleProfilesConnection(@PathVariable Long id) { return profilesConnectionService.readProfileConnection(id); }

    // Update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Update a connection"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void updateProfileConnection(@PathVariable Long id, @RequestBody ProfilesConnectionRequest updateProfileConnectionRequest){
        profilesConnectionService.updateProfilesConnections(id, updateProfileConnectionRequest);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Swagger documentation
    @Operation(
            summary = "Delete a connection"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    public void deleteProfileConnection(@PathVariable Long id) { profilesConnectionService.deleteProfileConnection(id); }
}
