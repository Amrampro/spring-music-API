package com.ecoleittp.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// This is a dto for "Song" Class in "music-service" Microservice
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDtoE {
    private Long id;
    private String title;
    private String description;

    private Long artist_id;
    private Long album_id;
}