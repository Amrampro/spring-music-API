package com.ecoleit.musicmicroservice.controller;

import com.ecoleit.musicmicroservice.dto.ArtistRequest;
import com.ecoleit.musicmicroservice.dto.ArtistResponse;
import com.ecoleit.musicmicroservice.dto.UserDtoE;
import com.ecoleit.musicmicroservice.service.ArtistService;
import com.ecoleit.musicmicroservice.service.UserServiceE;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/api/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    private final UserServiceE userService;

    // Create a new Artist
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createArtist(@RequestBody ArtistRequest artistRequest){
        artistService.createArtist(artistRequest);
    }

    // Get artist list
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistResponse> getAllArtist() { return artistService.getAllArtists(); }

    // Get single artist
    @GetMapping("/{artistId}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistResponse getSingleArtist(@PathVariable Long artistId) { return artistService.getSingleArtist(artistId); }

    // Delete artist
    @DeleteMapping("/{artistId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Long artistId) { artistService.deleteArtist(artistId);}

    // Test user id
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoE searchUser(@PathVariable Long userId) { return userService.findById(userId);}

}
