package com.ecoleit.musicmicroservice.service;

import com.ecoleit.musicmicroservice.dto.UserDtoE;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceE {
    private final RestTemplate restTemplate;

    public UserServiceE(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // find single id
    public UserDtoE findById(Long userId) {
        String url = "http://localhost:8081/api/users/" + userId;

        return restTemplate.getForObject(url, UserDtoE.class);
    }
}
