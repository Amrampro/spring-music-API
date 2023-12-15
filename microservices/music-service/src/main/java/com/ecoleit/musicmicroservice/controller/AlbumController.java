package com.ecoleit.musicmicroservice.controller;

import com.ecoleit.musicmicroservice.dto.AlbumRequest;
import com.ecoleit.musicmicroservice.dto.AlbumResponse;
import com.ecoleit.musicmicroservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    // Create a new Album
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAlbum (@RequestBody AlbumRequest albumRequest){ albumService.createAlbum(albumRequest);}

    // Get list of albums
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumResponse> getAllAlbums(){ return albumService.getAllAlbums();}

    // Get single album
    @GetMapping("/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumResponse getSingleAlbum(@PathVariable Long albumId){ return albumService.getSingleAlbum(albumId);}

    // Delete album
    @DeleteMapping("/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long albumId){albumService.deleteAlbum(albumId);}

}
