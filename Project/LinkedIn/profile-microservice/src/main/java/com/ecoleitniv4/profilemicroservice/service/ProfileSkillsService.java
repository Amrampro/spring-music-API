package com.ecoleitniv4.profilemicroservice.service;

import com.ecoleitniv4.profilemicroservice.dto.ProfilesConnectionRequest;
import com.ecoleitniv4.profilemicroservice.dto.ProfilesSkillsRequest;
import com.ecoleitniv4.profilemicroservice.dto.ProfilesSkillsResponse;
import com.ecoleitniv4.profilemicroservice.model.ProfilesSkills;
import com.ecoleitniv4.profilemicroservice.repository.ProfileSkillsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileSkillsService {
    private final ProfileSkillsRepository profileSkillsRepository;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (profileSkillsRepository.existsById(id)) {
            return true;
        } else {
            log.info("ProfileSkills " + id + " does not exist");
            return false;
        }
    }

    // Testing non null
    public boolean isNull(Long id) {
        if (id == null) {
            log.info("A Required data inserted is null");
            return false;
        } else {
            return true;
        }
    }

    // Retrieving information
    private ProfilesSkillsResponse mapToProfilesSkillsResponse(ProfilesSkills profilesSkills) {
        return ProfilesSkillsResponse.builder()
                .id(profilesSkills.getId())
                .profile_id(profilesSkills.getProfile_id())
                .skill_id(profilesSkills.getSkill_id())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create
    public void createProfileSkills(ProfilesSkillsRequest profilesSkillsRequest) {
        boolean status = true;

        // Profiles should exist and not be null
        // Skills should exist and not be null

        status = isNull(profilesSkillsRequest.getProfile_id());
        status = isNull(profilesSkillsRequest.getSkill_id());

        log.info("Creation status: " + status);

        if (status){
            ProfilesSkills profilesSkills = ProfilesSkills.builder()
                    .profile_id(profilesSkillsRequest.getProfile_id())
                    .skill_id(profilesSkillsRequest.getSkill_id())
                    .build();

            profileSkillsRepository.save(profilesSkills);
            log.info("ProfilesSkills : " + profilesSkills.getId() + " is save successfully");
        }
    }

    // Read all
    public List<ProfilesSkillsResponse> readAllProfilesSkills(){
        List<ProfilesSkills> profilesSkills;
        profilesSkills = profileSkillsRepository.findAll();

        return profilesSkills.stream().map(this::mapToProfilesSkillsResponse).toList();
    }

    // Read single
    public ProfilesSkillsResponse readSingleProfilesSkills(Long id){
        if (dataExists(id)){
            ProfilesSkills profilesSkills = profileSkillsRepository.findById(id).get();
            return mapToProfilesSkillsResponse(profilesSkills);
        } else {
            return null;
        }
    }

    // Update single
    public void updateProfilesSkills(Long id, ProfilesSkillsRequest updateProfilesSkillsRequest){
        boolean status = true;

        if (dataExists(id)){
            ProfilesSkills existingData = profileSkillsRepository.findById(id).get();

            // Not existing skills or profile are handled here

            log.info("Creation status: " + status);

            if (status) {
                existingData.setProfile_id(updateProfilesSkillsRequest.getProfile_id());
                existingData.setSkill_id(updateProfilesSkillsRequest.getSkill_id());

                profileSkillsRepository.save(existingData);
                log.info("ProfileSkills: " + id + " updated successfully");
            }

        }
    }

    // Delete Data
    public void deleteProfileSkills(Long id){
        if (dataExists(id)){
            profileSkillsRepository.deleteById(id);
        }
    }
}
