package com.ecoleittp.userservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song_playlists")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long song_id;
    private Long playlist_id;

    // Complete other micro service before coming back to this one
/*
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;*/

}
