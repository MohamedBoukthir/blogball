package com.mohamed.blogball.Repositories;

import com.mohamed.blogball.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
