package com.ecoleitniv4.profilemicroservice.repository;

import com.ecoleitniv4.profilemicroservice.model.ProfilesSkills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileSkillsRepository extends JpaRepository<ProfilesSkills, Long> {
}
