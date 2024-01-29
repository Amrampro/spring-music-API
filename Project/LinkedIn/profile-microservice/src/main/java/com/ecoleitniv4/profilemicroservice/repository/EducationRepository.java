package com.ecoleitniv4.profilemicroservice.repository;

import com.ecoleitniv4.profilemicroservice.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
