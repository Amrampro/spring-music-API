package com.ecoleittp.userservice.repository;

import com.ecoleittp.userservice.model.SongPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongPlaylistRepository extends JpaRepository<SongPlaylist, Long> {
}
