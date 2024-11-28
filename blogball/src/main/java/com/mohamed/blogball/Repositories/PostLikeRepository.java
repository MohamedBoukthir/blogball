package com.mohamed.blogball.Repositories;

import com.mohamed.blogball.model.likes.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserIdAndPostId(Long userId, Long postId);
    long countByPostId(Long postId);

}
