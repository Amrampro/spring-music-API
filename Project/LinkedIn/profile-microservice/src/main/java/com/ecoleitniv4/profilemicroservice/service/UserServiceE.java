package com.ecoleitniv4.profilemicroservice.service;

import com.ecoleitniv4.profilemicroservice.dto.UserDTOE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceE {

    public final RestTemplate restTemplate;

    public UserServiceE(RestTemplate restTemplate){ this.restTemplate = restTemplate; }

    // Find a user if it exists
    public UserDTOE findUser(Long profileId) {
        // Use the service name as hostname
        String url ="http://localhost:8081/api/users/" + profileId;
        return restTemplate.getForObject(url, UserDTOE.class);
    }

    public boolean userTest(Long id){
        if (id == null){
            log.info("User ID CANNOT be null");
            return false;
        } else {
            if (findUser(id) == null){
                log.info("No user found with ID : " + id);
                return false;
            } else {
                return true;
            }
        }
    }
}
