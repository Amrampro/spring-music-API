package com.ecoleittp.userservice.service;


import com.ecoleittp.userservice.dto.PlaylistRequest;
import com.ecoleittp.userservice.dto.PlaylistResponse;
import com.ecoleittp.userservice.model.Playlist;
import com.ecoleittp.userservice.repository.PlaylistRepository;
import com.ecoleittp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    // Create a new Playlist
    public void createPlaylist(PlaylistRequest playlistRequest){
        boolean create = true;

        // Test if user exists
        if (playlistRequest.getUserId() == null){
            create = false;
            log.info("User id CANNOT BE NULL");
        } else {
            if (userRepository.existsById(playlistRequest.getUserId())){
                create = true;
            } else {
                create = false;
                log.info("User " + playlistRequest.getUserId() + " does not exist");
            }
        }

        log.info("Creation authorized: " + create);

        if (create == true){
            Playlist playlist = Playlist.builder()
                    .title(playlistRequest.getTitle())
                    .description(playlistRequest.getDescription())
                    .userId(playlistRequest.getUserId())
                    .build();

            playlistRepository.save(playlist);
            log.info("Playlist " + playlist.getId() + " is saved successfully");
        }
    }

    //
    public List<PlaylistResponse> getAllPlaylists(){
        List<Playlist> playlistLists;
        playlistLists =  playlistRepository.findAll();

        return playlistLists.stream().map(this::mapToPlaylistResponse).toList();
    }

    private PlaylistResponse mapToPlaylistResponse(Playlist playlist) {
        return PlaylistResponse.builder()
                .id(playlist.getId())
                .title(playlist.getTitle())
                .description(playlist.getDescription())
                .build();
    }

    // Get single Playlist
    public PlaylistResponse getSinglePlaylist(Long playlistId) {
        if (playlistRepository.existsById(playlistId)) {
            Playlist playlist = playlistRepository.findById(playlistId).get();
            return mapToPlaylistResponse(playlist);
        } else {
            log.info("Playlist not found with id " + playlistId);
            return null;
        }
    }

    // Delete a playlist
    public void deletePlaylist(Long playlistId) {
        // Check if the playlist exists before deleting
        if(playlistRepository.existsById(playlistId)){
            playlistRepository.deleteById(playlistId);
        } else {
            // Handle case where the playlist does not exist
            // throw new IllegalStateException("The playlist " + playlistId + " does not exist");
            log.info("The playlist " + playlistId + " does not exist");
        }
    }

}
