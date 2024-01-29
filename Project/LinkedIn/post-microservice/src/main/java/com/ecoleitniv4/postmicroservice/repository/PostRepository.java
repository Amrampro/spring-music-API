package com.ecoleitniv4.postmicroservice.repository;

import com.ecoleitniv4.postmicroservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
