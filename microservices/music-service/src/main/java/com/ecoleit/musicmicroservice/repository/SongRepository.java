package com.ecoleit.musicmicroservice.repository;

import com.ecoleit.musicmicroservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
