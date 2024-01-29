package com.ecoleitniv4.postmicroservice.repository;

import com.ecoleitniv4.postmicroservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
