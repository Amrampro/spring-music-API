package com.ecoleit.musicmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "albums")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Long artist;

    /*
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    */
}
