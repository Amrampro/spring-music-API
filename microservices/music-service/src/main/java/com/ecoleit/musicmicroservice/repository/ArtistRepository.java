package com.ecoleit.musicmicroservice.repository;

import com.ecoleit.musicmicroservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long > {
}
