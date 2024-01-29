package com.ecoleitniv4.profilemicroservice.service;

import com.ecoleitniv4.profilemicroservice.dto.ExperienceRequest;
import com.ecoleitniv4.profilemicroservice.dto.ExperienceResponse;
import com.ecoleitniv4.profilemicroservice.model.Experience;
import com.ecoleitniv4.profilemicroservice.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperiencesService {
    private final ExperienceRepository experienceRepository;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (experienceRepository.existsById(id)) {
            return true;
        } else {
            log.info("Experience " + id + " does not exist");
            return false;
        }
    }

    // Retrieving experience information
    private ExperienceResponse mapToExperienceResponse(Experience experience) {
        return ExperienceResponse.builder()
                .id(experience.getId())
                .profile_id(experience.getProfile_id())
                .job_title(experience.getJob_title())
                .company_name(experience.getCompany_name())
                .location(experience.getLocation())
                .start_date(experience.getStart_date())
                .end_date(experience.getEnd_date())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create a new
    public void createExperience(ExperienceRequest experienceRequest) {
        boolean status = true;

        // profile_id is required
        if (experienceRequest.getProfile_id() == null) {
            status = false;
            log.info("profile_id should not be null");
        }

        if (status) {
            Experience experience = Experience.builder()
                    .profile_id(experienceRequest.getProfile_id())
                    .job_title(experienceRequest.getJob_title())
                    .company_name(experienceRequest.getCompany_name())
                    .location(experienceRequest.getLocation())
                    .start_date(experienceRequest.getStart_date())
                    .end_date(experienceRequest.getEnd_date())
                    .build();

            experienceRepository.save(experience);
            log.info("Experience: " + experience.getId() + " created with success");
        }
    }

    // Read all
    public List<ExperienceResponse> readAllExperience() {
        List<Experience> experience;
        experience = experienceRepository.findAll();

        return experience.stream().map(this::mapToExperienceResponse).toList();
    }

    // Read single
    public ExperienceResponse readSingleExperience(Long id) {
        if (dataExists(id)) {
            Experience experience = experienceRepository.findById(id).get();
            return mapToExperienceResponse(experience);
        } else {
            return null;
        }
    }

    // Put single experience
    public void updateExperience(Long id, ExperienceRequest updateExperienceRequest) {
        boolean status = true;

        if (dataExists(id)) {
            Experience existExperience = experienceRepository.findById(id).get();

            // Not null and existing profile
            log.info("Creation status: " + status);

            if (status) {
                existExperience.setProfile_id(updateExperienceRequest.getProfile_id());
                existExperience.setJob_title(updateExperienceRequest.getJob_title());
                existExperience.setCompany_name(updateExperienceRequest.getCompany_name());
                existExperience.setLocation(updateExperienceRequest.getLocation());
                existExperience.setStart_date(updateExperienceRequest.getStart_date());
                existExperience.setEnd_date(updateExperienceRequest.getEnd_date());

                experienceRepository.save(existExperience);
                log.info("Experience saved: " + id);
            }
        }
    }

    // Delete Education
    public void deleteExperience(Long id) {
        if (dataExists(id)) {
            experienceRepository.deleteById(id);
        }
    }
}
