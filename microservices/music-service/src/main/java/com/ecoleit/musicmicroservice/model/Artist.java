package com.ecoleit.musicmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artists")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long id_user;
}
