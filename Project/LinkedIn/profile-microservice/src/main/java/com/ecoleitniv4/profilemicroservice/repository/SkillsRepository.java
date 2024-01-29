package com.ecoleitniv4.profilemicroservice.repository;

import com.ecoleitniv4.profilemicroservice.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
}
