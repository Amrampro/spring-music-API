package com.ecoleittp.userservice.controller;

import com.ecoleittp.userservice.dto.PlaylistRequest;
import com.ecoleittp.userservice.dto.PlaylistResponse;
import com.ecoleittp.userservice.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    public final PlaylistService playlistService;

    // Create a new Playlist
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlaylist(@RequestBody PlaylistRequest playlistRequest){
        playlistService.createPlaylist(playlistRequest);
    }

    // Get Playlist list
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlaylistResponse> getAllPlaylists() {return playlistService.getAllPlaylists();}

    // Get single Playlist
    @GetMapping("/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistResponse getSinglePlaylist(@PathVariable Long playlistId) {return playlistService.getSinglePlaylist(playlistId);}

    // Delete a Playlist
    @DeleteMapping("/{playlistId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaylist(@PathVariable Long playlistId){playlistService.deletePlaylist(playlistId);}

}
