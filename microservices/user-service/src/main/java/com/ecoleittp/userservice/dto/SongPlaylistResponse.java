package com.ecoleittp.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongPlaylistResponse {
    private Long id;
    private Long song_id;
    private Long playlist_id;
}
