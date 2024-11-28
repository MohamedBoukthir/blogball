package com.mohamed.blogball.Repositories;

import com.mohamed.blogball.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
