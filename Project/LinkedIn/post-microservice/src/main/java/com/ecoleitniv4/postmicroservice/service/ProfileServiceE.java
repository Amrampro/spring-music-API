// This is the service of the class "ProfileDTO" in the dto package
package com.ecoleitniv4.postmicroservice.service;

import com.ecoleitniv4.postmicroservice.dto.ProfileDTOe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProfileServiceE {
    private final RestTemplate restTemplate;

    public ProfileServiceE(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    // Find a profile if it exists
    public ProfileDTOe findProfile(Long profileId) {
        // Use the service name as hostname
        String url ="http://localhost:8082/api/profiles/" + profileId;
        return restTemplate.getForObject(url, ProfileDTOe.class);
    }

    public boolean profileTest(Long id){
        if (id == null){
            log.info("Profile ID CANNOT be null");
            return false;
        } else {
            if (findProfile(id) == null){
                log.info("No profile found with ID : " + id);
                return false;
            } else {
                return true;
            }
        }
    }
}
