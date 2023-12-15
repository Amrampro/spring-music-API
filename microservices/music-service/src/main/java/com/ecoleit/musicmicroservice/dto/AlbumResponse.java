package com.ecoleit.musicmicroservice.dto;

import com.ecoleit.musicmicroservice.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private Long id;
    private String title;
    private String description;
    //private Artist artist;

    private Long artist;
}
