package com.ecoleittp.userservice.repository;

import com.ecoleittp.userservice.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
