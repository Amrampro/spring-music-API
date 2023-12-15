package com.ecoleittp.userservice.repository;

import com.ecoleittp.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
