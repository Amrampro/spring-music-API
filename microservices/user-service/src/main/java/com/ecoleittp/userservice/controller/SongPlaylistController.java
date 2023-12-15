package com.ecoleittp.userservice.controller;

import com.ecoleittp.userservice.dto.SongDtoE;
import com.ecoleittp.userservice.dto.SongPlaylistRequest;
import com.ecoleittp.userservice.dto.SongPlaylistResponse;
import com.ecoleittp.userservice.service.SongPlaylistService;
import com.ecoleittp.userservice.service.SongServiceE;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api/songPlaylists")
@RequiredArgsConstructor
public class SongPlaylistController {
    public final SongPlaylistService songPlaylistService;
    public final SongServiceE songServiceE;

    // Create a new songPlayList
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSongPlaylist(@RequestBody SongPlaylistRequest songPlaylistRequest){ songPlaylistService.createSongPlaylist(songPlaylistRequest);}

    // Show the songPlaylist
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SongPlaylistResponse> getAllSongPlaylists(){ return songPlaylistService.getAllSongPlaylists();}

    // Show single songPlaylist
    @GetMapping("/{songPlaylistId}")
    @ResponseStatus(HttpStatus.OK)
    public SongPlaylistResponse getSingleSongPlaylist(@PathVariable Long songPlaylistId){ return songPlaylistService.getSingleSongPlaylist(songPlaylistId);}

    // Delete songPlaylist
    @DeleteMapping("/{songPlaylistId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSongPlaylist(@PathVariable Long songPlaylistId) { songPlaylistService.deleteSingleSongPlaylist(songPlaylistId);}

    //Test an existing song in another microservice
    @GetMapping("/song/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SongDtoE findSongById(@PathVariable Long id) { return songServiceE.findSongId(id);}
}
