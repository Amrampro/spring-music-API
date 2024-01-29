package com.ecoleitniv4.profilemicroservice.service;

import com.ecoleitniv4.profilemicroservice.dto.ProfilesConnectionRequest;
import com.ecoleitniv4.profilemicroservice.dto.ProfilesConnectionResponse;
import com.ecoleitniv4.profilemicroservice.model.ProfilesConnection;
import com.ecoleitniv4.profilemicroservice.repository.ProfilesConnectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfilesConnectionService {
    private final ProfilesConnectionRepository profilesConnectionRepository;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (profilesConnectionRepository.existsById(id)) {
            return true;
        } else {
            log.info("ProfilesConnection " + id + " does not exist");
            return false;
        }
    }

    // Testing non null
    public boolean isNull(Long id) {
        if (id == null){
            log.info("A data inserted is null");
            return false;
        } else {
            return true;
        }
    }

    // Retrieving information
    private ProfilesConnectionResponse mapToProfilesConnectionResponse(ProfilesConnection profilesConnection){
        return ProfilesConnectionResponse.builder()
                .id(profilesConnection.getId())
                .profile1_id(profilesConnection.getProfile1_id())
                .profile2_id(profilesConnection.getProfile2_id())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create a new profile
    public void createProfileSConnections(ProfilesConnectionRequest profilesConnectionRequest){
        boolean status = true;

        // Profiles should exist and not be null
        status = isNull(profilesConnectionRequest.getProfile1_id());
        if (status){ status = isNull(profilesConnectionRequest.getProfile2_id()); }

        log.info("Creation status: " + status);

        if (status == true){
            ProfilesConnection profilesConnection = ProfilesConnection.builder()
                    .profile1_id(profilesConnectionRequest.getProfile1_id())
                    .profile2_id(profilesConnectionRequest.getProfile2_id())
                    .build();

            profilesConnectionRepository.save(profilesConnection);
            log.info("ProfilesConnection : " + profilesConnection.getId() + " is save successfully");
        }
    }

    // Read all profiles
    public List<ProfilesConnectionResponse> readAllProfilesConnection(){
        List<ProfilesConnection> profilesConnections;
        profilesConnections = profilesConnectionRepository.findAll();

        return profilesConnections.stream().map(this::mapToProfilesConnectionResponse).toList();
    }

    // read single
    public ProfilesConnectionResponse readProfileConnection(Long id){
        if (dataExists(id)){
            ProfilesConnection profilesConnection = profilesConnectionRepository.findById(id).get();
            return mapToProfilesConnectionResponse(profilesConnection);
        } else {
            return null;
        }
    }

    // Update single
    public void updateProfilesConnections(Long id, ProfilesConnectionRequest updateProfilesConnectionRequest){
        boolean status = true;

        if (dataExists(id)){
            ProfilesConnection existingData = profilesConnectionRepository.findById(id).get();

            // Not null and existing profiles
            status = isNull(updateProfilesConnectionRequest.getProfile1_id());
            if (status){ status = isNull(updateProfilesConnectionRequest.getProfile2_id()); }

            log.info("Creation status: " + status);

            if (status){
                existingData.setProfile1_id(updateProfilesConnectionRequest.getProfile1_id());
                existingData.setProfile2_id(updateProfilesConnectionRequest.getProfile2_id());

                profilesConnectionRepository.save(existingData);
                log.info("ProfileConnection: " + id + " updated successfully");
            }
        }
    }

    // Delete data
    public void deleteProfileConnection(Long id){
        if (dataExists(id)){
            profilesConnectionRepository.deleteById(id);
        }
    }
}
