package com.ecoleit.musicmicroservice.repository;

import com.ecoleit.musicmicroservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
