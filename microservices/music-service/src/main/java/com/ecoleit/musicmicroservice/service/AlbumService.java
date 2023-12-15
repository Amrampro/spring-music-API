package com.ecoleit.musicmicroservice.service;

import com.ecoleit.musicmicroservice.dto.AlbumRequest;
import com.ecoleit.musicmicroservice.dto.AlbumResponse;
import com.ecoleit.musicmicroservice.model.Album;
import com.ecoleit.musicmicroservice.repository.AlbumRepository;
import com.ecoleit.musicmicroservice.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumService {
    private final AlbumRepository albumRepository;

    private final ArtistRepository artistRepository;

    private Long artist = null;

    // Create album
    public void createAlbum(AlbumRequest albumRequest) {
        if (artistRepository.existsById(albumRequest.getArtist())){
            artist = albumRequest.getArtist();
        }

        Album album = Album.builder()
                .title(albumRequest.getTitle())
                .description(albumRequest.getDescription())
                .artist(artist)
                .build();

        albumRepository.save(album);
        log.info("Album " + album.getId() + " created successfully");
    }

    // List of albums
    public List<AlbumResponse> getAllAlbums() {
        List<Album> albums;
        albums = albumRepository.findAll();

        return albums.stream().map(this::mapToAlbumResponse).toList();
    }

    private AlbumResponse mapToAlbumResponse(Album album) {
        return AlbumResponse.builder()
                .id(album.getId())
                .title(album.getTitle())
                .description(album.getDescription())
                .artist(album.getArtist())
                .build();
    }

    // Get single album
    public AlbumResponse getSingleAlbum(Long albumId) {
        if (albumRepository.existsById(albumId)) {
            Album album = albumRepository.findById(albumId).get();
            return mapToAlbumResponse(album);
        } else {
            log.info("Album not found with id: " + albumId);
            return null;
        }
    }

    // Delete album
    public void deleteAlbum(Long albumId){
        // Check if it exists
        if (albumRepository.existsById(albumId)){
            albumRepository.deleteById(albumId);
        } else {
            log.info("Album not found with id: " + albumId);
        }
    }
}
