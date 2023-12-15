package com.ecoleit.musicmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongRequest {
    private String title;
    private String description;

    private Long artist_id;
    private Long album_id;

}
