package com.ecoleitniv4.profilemicroservice.service;

import com.ecoleitniv4.profilemicroservice.dto.ProfileRequest;
import com.ecoleitniv4.profilemicroservice.dto.ProfileResponse;
import com.ecoleitniv4.profilemicroservice.model.Profile;
import com.ecoleitniv4.profilemicroservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;

    // For User from an external microservice
    private final UserServiceE userService;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (profileRepository.existsById(id)) {
            return true;
        } else {
            log.info("Profile " + id + " does not exist");
            return false;
        }
    }

    // Retrieving profile information
    private ProfileResponse mapToProfileResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .user_id(profile.getUser_id())
                .current_job_title(profile.getCurrent_job_title())
                .industry(profile.getIndustry())
                .summary(profile.getSummary())
                .headline(profile.getHeadline())
                .website(profile.getWebsite())
                .open_for_work(profile.getOpen_for_work())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create a new profile
    public void createProfile(ProfileRequest profileRequest) {
        boolean status = true;
        // user_id should not be null

        status = userService.userTest(profileRequest.getUser_id());

        log.info("Creation status: " + status);

        if (status == true) {
            Profile profile = Profile.builder()
                    .user_id(profileRequest.getUser_id())
                    .current_job_title(profileRequest.getCurrent_job_title())
                    .industry(profileRequest.getIndustry())
                    .summary(profileRequest.getSummary())
                    .headline(profileRequest.getHeadline())
                    .website(profileRequest.getWebsite())
                    .open_for_work(profileRequest.getOpen_for_work())
                    .build();

            profileRepository.save(profile);
            log.info("Profile : " + profile.getId() + " is save successfully");
        }
    }

    // Read all profiles
    public List<ProfileResponse> readAllProfiles() {
        List<Profile> profiles;
        profiles = profileRepository.findAll();

        return profiles.stream().map(this::mapToProfileResponse).toList();
    }

    // Read Single profile
    public ProfileResponse readProfile(Long id) {
        if (dataExists(id)) {
            Profile profile = profileRepository.findById(id).get();
            return mapToProfileResponse(profile);
        } else {
            return null;
        }
    }

    // Update single profile
    public void updateProfile(Long profileId, ProfileRequest updateProfileRequest) {
        boolean status = true;

        if (dataExists(profileId)) {
            Profile existingProfile = profileRepository.findById(profileId).get();

            // Not null and Existing user_id
            status = userService.userTest(updateProfileRequest.getUser_id());

            log.info("Creation status: " + status);

            if (status) {
                existingProfile.setUser_id(updateProfileRequest.getUser_id());
                existingProfile.setCurrent_job_title(updateProfileRequest.getCurrent_job_title());
                existingProfile.setIndustry(updateProfileRequest.getIndustry());
                existingProfile.setSummary(updateProfileRequest.getSummary());
                existingProfile.setHeadline(updateProfileRequest.getHeadline());
                existingProfile.setWebsite(updateProfileRequest.getWebsite());
                existingProfile.setOpen_for_work(updateProfileRequest.getOpen_for_work());

                profileRepository.save(existingProfile);
                log.info("Profile: " + profileId + " updated successfully");
            }
        }
    }

    // Delete profile
    public void deleteProfile(Long profileId){
        if (dataExists(profileId)){
            profileRepository.deleteById(profileId);
        }
    }
}
