package com.ecoleittp.userservice.service;

import com.ecoleittp.userservice.dto.SongDtoE;
import com.ecoleittp.userservice.dto.SongPlaylistRequest;
import com.ecoleittp.userservice.dto.SongPlaylistResponse;
import com.ecoleittp.userservice.model.SongPlaylist;
import com.ecoleittp.userservice.repository.PlaylistRepository;
import com.ecoleittp.userservice.repository.SongPlaylistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongPlaylistService {
    private final SongPlaylistRepository songPlaylistRepository;

    private final PlaylistRepository playlistRepository;

    private final SongServiceE songServiceE;

    private Long playlist_id = null;

    // Create a new songPlaylist
    public void createSongPlaylist(SongPlaylistRequest songPlaylistRequest){
        // Search if playlist exists

        boolean create = true; // Boolean has to be true for the creation to be made

        if (playlistRepository.existsById(songPlaylistRequest.getPlaylist_id())){
            playlist_id = songPlaylistRequest.getPlaylist_id();
        } else {
            create = false;
            log.info("playlist with id: " + songPlaylistRequest.getPlaylist_id() + " DOES NOT exist in table 'playlist'");
        }

        SongDtoE searchUserId =  songServiceE.findSongId(songPlaylistRequest.getSong_id());
        if (searchUserId != null){
            // log.info("Song with id: " + songPlaylistRequest.getSong_id() + " exists");
        } else {
            create = false;
            log.info("Song with id: " + songPlaylistRequest.getSong_id() + " DOES NOT EXIST in table 'song'");
        }

        log.info("The Creation authorization: " + create + " -");

        if (create == true) {
            SongPlaylist songPlaylist = SongPlaylist.builder()
                    .song_id(songPlaylistRequest.getSong_id())
                    .playlist_id(playlist_id)
                    .build();

            songPlaylistRepository.save(songPlaylist);
            // log.info("SongPlaylist " + songPlaylist.getId() + " is saved successfully");
            log.info("SongPlaylist is saved successfully !");
        }

    }

    // Get list of songPlaylist
    public List<SongPlaylistResponse> getAllSongPlaylists() {
        List<SongPlaylist> songPlaylists = songPlaylistRepository.findAll();
        return songPlaylists.stream().map(this::mapToSongPlaylistResponse).toList();
    }

    private SongPlaylistResponse mapToSongPlaylistResponse(SongPlaylist songPlaylist) {
        return SongPlaylistResponse.builder()
                .id(songPlaylist.getId())
                .song_id(songPlaylist.getSong_id())
                .playlist_id(songPlaylist.getPlaylist_id())
                .build();
    }

    // Get single songPlaylist
    public SongPlaylistResponse getSingleSongPlaylist(Long songPlaylistId){
        if (songPlaylistRepository.existsById(songPlaylistId)){
            SongPlaylist songPlaylist = songPlaylistRepository.findById(songPlaylistId).get();

            return mapToSongPlaylistResponse(songPlaylist);
        } else {
            log.info("SongPlaylist with id " + songPlaylistId + " Not found");
            return null;
        }
    }

    // Delete single songPlaylist
    public void deleteSingleSongPlaylist(Long songPlaylistId){
        if (songPlaylistRepository.existsById(songPlaylistId)){
            songPlaylistRepository.deleteById(songPlaylistId);
        } else {
            log.info("SongPlaylist with id " + songPlaylistId + " not found");
        }
    }
}
