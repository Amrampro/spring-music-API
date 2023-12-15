package com.ecoleit.musicmicroservice.controller;

import com.ecoleit.musicmicroservice.dto.SongRequest;
import com.ecoleit.musicmicroservice.dto.SongResponse;
import com.ecoleit.musicmicroservice.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    // Create a new Song
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSong(@RequestBody SongRequest songRequest){ songService.createSong(songRequest);}

    // Show All song
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SongResponse> getAllSongs(){ return songService.getAllSongs();}

    // Get single song
    @GetMapping("/{songId}")
    @ResponseStatus(HttpStatus.OK)
    public SongResponse getSingleSong(@PathVariable Long songId){ return songService.getSingleSong(songId);}

    // Delete song
    @DeleteMapping("/{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable Long songId){ songService.deleteSong(songId);}
}
