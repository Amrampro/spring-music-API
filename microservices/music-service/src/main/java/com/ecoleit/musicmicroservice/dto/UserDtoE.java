package com.ecoleit.musicmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// This is a dto for "User" Class in "user-service" Microservice
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoE {
    private Long id;
    private String username;
    private String email;
}