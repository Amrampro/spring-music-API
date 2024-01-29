package com.ecoleitniv4.profilemicroservice.repository;

import com.ecoleitniv4.profilemicroservice.model.ProfilesConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesConnectionRepository extends JpaRepository<ProfilesConnection, Long> {
}
