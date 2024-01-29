package com.ecoleitniv4.profilemicroservice.service;

import com.ecoleitniv4.profilemicroservice.dto.EducationRequest;
import com.ecoleitniv4.profilemicroservice.dto.EducationResponse;
import com.ecoleitniv4.profilemicroservice.model.Education;
import com.ecoleitniv4.profilemicroservice.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationService {
    private final EducationRepository educationRepository;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (educationRepository.existsById(id)) {
            return true;
        } else {
            log.info("Education " + id + " does not exist");
            return false;
        }
    }

    // Retrieving education information
    private EducationResponse mapToEducationResponse(Education education){
        return  EducationResponse.builder()
                .id(education.getId())
                .profile_id(education.getProfile_id())
                .school(education.getSchool())
                .degree(education.getDegree())
                .field_of_study(education.getField_of_study())
                .start_date(education.getStart_date())
                .end_date(education.getEnd_date())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create a new education
    public void createEducation(EducationRequest educationRequest){
        boolean status = true;

        // profile_id is required
        if (educationRequest.getProfile_id() == null){
            status = false;
            log.info("profile_id should not be null");
        }

        if (status){
            Education education = Education.builder()
                    .profile_id(educationRequest.getProfile_id())
                    .school(educationRequest.getSchool())
                    .degree(educationRequest.getDegree())
                    .field_of_study(educationRequest.getField_of_study())
                    .start_date(educationRequest.getStart_date())
                    .end_date(educationRequest.getEnd_date())
                    .build();

            educationRepository.save(education);
            log.info("Education: " + education.getId() + " created with success!");
        }
    }

    // Read all
    public List<EducationResponse> readAllEducation() {
        List<Education> education;
        education = educationRepository.findAll();

        return education.stream().map(this::mapToEducationResponse).toList();
    }

    // Read Single
    public EducationResponse readEducation(Long id) {
        if (dataExists(id)) {
            Education education = educationRepository.findById(id).get();
            return mapToEducationResponse(education);
        } else {
            return null;
        }
    }

    // Put single education
    public void updateEducation(Long id, EducationRequest updateEducationRequest){
        boolean status = true;

        if (dataExists(id)){
            Education existEducation = educationRepository.findById(id).get();

            // Not null and existing profile
            log.info("Creation status: " + status);

            if (status){
                existEducation.setProfile_id(updateEducationRequest.getProfile_id());
                existEducation.setSchool(updateEducationRequest.getSchool());
                existEducation.setDegree(updateEducationRequest.getDegree());
                existEducation.setField_of_study(updateEducationRequest.getField_of_study());
                existEducation.setStart_date(updateEducationRequest.getStart_date());
                existEducation.setEnd_date(updateEducationRequest.getEnd_date());

                educationRepository.save(existEducation);
                log.info("Education: " + id + " updated succesfully !");
            }
        }
    }

    // Delete Education
    public void deleteEducation(Long id){
        if (dataExists(id)){
            educationRepository.deleteById(id);
        }
    }

}
