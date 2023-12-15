package com.ecoleit.musicmicroservice.service;

import com.ecoleit.musicmicroservice.dto.ArtistRequest;
import com.ecoleit.musicmicroservice.dto.ArtistResponse;
import com.ecoleit.musicmicroservice.dto.UserDtoE;
import com.ecoleit.musicmicroservice.model.Artist;
import com.ecoleit.musicmicroservice.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistService {
    private final ArtistRepository artistRepository;

    private final UserServiceE userServiceE;
    private UserDtoE userDtoE;

    //create artist
    public void createArtist(ArtistRequest artistRequest) {

        boolean create = true;

        // Test if user exists
        userDtoE = userServiceE.findById(artistRequest.getId_user());
        if (userDtoE == null){
            create = false;
            log.info("User with id: " + artistRequest.getId_user() + " not found");
        }

        log.info("Creation authorize is: " + create);

        if (create == true){
            Artist artist = Artist.builder()
                    .id_user(artistRequest.getId_user())
                    .build();

            artistRepository.save(artist);
            log.info("Artist " + artist.getId() + " created successfully");
        }
    }

    //List of artists
    public List<ArtistResponse> getAllArtists(){
        List<Artist> artists;
        artists = artistRepository.findAll();

        return artists.stream().map(this::mapToArtistResponse).toList();
    }

    private ArtistResponse mapToArtistResponse(Artist artist) {
        return ArtistResponse.builder()
                .id(artist.getId())
                .id_user(artist.getId_user())
                .build();
    }

    //Get single artist
    public ArtistResponse getSingleArtist(Long artistId) {
        if (artistRepository.existsById(artistId)){
            Artist artist = artistRepository.findById(artistId).get();

            return mapToArtistResponse(artist);
        } else {
            log.info("Artist not found with id: " + artistId);
            return null;
        }

    }

    // Delete an Artist
    public void deleteArtist(Long artistId){
        // Check if artist exists
        if(artistRepository.existsById(artistId)){
            artistRepository.deleteById(artistId);
        } else {
            log.info("Artist not found with id: " + artistId);
        }
    }
}
