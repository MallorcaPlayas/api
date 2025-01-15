package org.example.apirest.repository;

import org.example.apirest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
}
