package com.ecoleit.musicmicroservice.service;

import com.ecoleit.musicmicroservice.dto.SongRequest;
import com.ecoleit.musicmicroservice.dto.SongResponse;
import com.ecoleit.musicmicroservice.model.Song;
import com.ecoleit.musicmicroservice.repository.AlbumRepository;
import com.ecoleit.musicmicroservice.repository.ArtistRepository;
import com.ecoleit.musicmicroservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {
    private final SongRepository songRepository;

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    private Long artist_id = null;
    private Long album_id = null;
    // Create a song
    public void createSong(SongRequest songRequest){
        if (artistRepository.existsById(songRequest.getArtist_id())){
            artist_id = songRequest.getArtist_id();
        }
        if (albumRepository.existsById(songRequest.getAlbum_id())){
            album_id = songRequest.getAlbum_id();
        }

        Song song = Song.builder()
                .title(songRequest.getTitle())
                .description(songRequest.getDescription())
                .artist_id(artist_id)
                .album_id(album_id)
                .build();

        songRepository.save(song);
        log.info("Song " + song.getId() + " created successfully");
    }

    // List of songs
    public List<SongResponse> getAllSongs(){
        List<Song> songs;
        songs = songRepository.findAll();

        return songs.stream().map(this::mapToSongResponse).toList();
    }

    private SongResponse mapToSongResponse(Song song) {
        return SongResponse.builder()
                .id(song.getId())
                .title(song.getTitle())
                .description(song.getDescription())
                .album_id(song.getAlbum_id())
                .artist_id(song.getArtist_id())
                .build();
    }

    // Get single song
    public SongResponse getSingleSong(Long songId) {
        if (songRepository.existsById(songId)){
            Song song = songRepository.findById(songId).get();
            return mapToSongResponse(song);
        } else {
            log.info("Song with id " + songId + " Could not be found");
            return null;
        }
    }

    // Delete song
    public void deleteSong(Long songId){
        if (songRepository.existsById(songId)){
            songRepository.deleteById(songId);
        } else {
            log.info("Song with id " + songId + " Could not be found");
        }
    }

}
