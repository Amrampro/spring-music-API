package com.ecoleitniv4.profilemicroservice.repository;

import com.ecoleitniv4.profilemicroservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
