package com.ecoleit.musicmicroservice.dto;

import com.ecoleit.musicmicroservice.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumRequest {
    private String title;
    private String description;
    //private Artist artist;

    private Long artist;
}
