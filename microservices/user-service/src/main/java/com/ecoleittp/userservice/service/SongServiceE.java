package com.ecoleittp.userservice.service;

import com.ecoleittp.userservice.dto.SongDtoE;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Template for Song in other Microservice
@Service
public class SongServiceE {
    private final RestTemplate restTemplate;

    public SongServiceE(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Find a song if exists
    public SongDtoE findSongId(Long id) {
        // Use the service name as HostName
        String url = "http://localhost:8080/api/songs/" + id;

        return restTemplate.getForObject(url, SongDtoE.class);
    }
}
