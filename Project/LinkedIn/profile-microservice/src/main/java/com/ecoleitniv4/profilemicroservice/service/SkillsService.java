package com.ecoleitniv4.profilemicroservice.service;

import com.ecoleitniv4.profilemicroservice.dto.SkillsRequest;
import com.ecoleitniv4.profilemicroservice.dto.SkillsResponse;
import com.ecoleitniv4.profilemicroservice.model.Skills;
import com.ecoleitniv4.profilemicroservice.repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkillsService {
    private final SkillsRepository skillsRepository;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (skillsRepository.existsById(id)) {
            return true;
        } else {
            log.info("Skill " + id + " does not exist");
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
    private SkillsResponse mapToSkillsResponse(Skills skills) {
        return SkillsResponse.builder()
                .id(skills.getId())
                .name(skills.getName())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create
    public void createSkills(SkillsRequest skillsRequest){
        boolean status = true;

        log.info("Creation status: " + status);

        if (status) {
            Skills skills = Skills.builder()
                    .name(skillsRequest.getName())
                    .build();

            skillsRepository.save(skills);
            log.info("Skills : " + skills.getId() + " is save successfully");
        }
    }

    // Read all
    public List<SkillsResponse> readAllSkills(){
        List<Skills> skills;
        skills = skillsRepository.findAll();

        return skills.stream().map(this::mapToSkillsResponse).toList();
    }

    // Read single
    public SkillsResponse readSingleSkill(Long id){
        if (dataExists(id)){
            Skills skills = skillsRepository.findById(id).get();
            return mapToSkillsResponse(skills);
        } else {
            return null;
        }
    }

    // Update single
    public void updateSkills(Long id, SkillsRequest updateSkillsRequest){
        boolean status = true;

        if (dataExists(id)){
            Skills existingData = skillsRepository.findById(id).get();

            log.info("Creation status: " + status);

            if (status) {
                existingData.setName(updateSkillsRequest.getName());

                skillsRepository.save(existingData);
                log.info("Skills: " + id + " updated successfully");
            }
        }
    }

    // Delete Data
    public void deleteSkills(Long id){
        if (dataExists(id)){
            skillsRepository.deleteById(id);
        }
    }
}
