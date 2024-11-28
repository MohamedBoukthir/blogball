package com.mohamed.blogball.Repositories;

import com.mohamed.blogball.model.likes.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByUserIdAndCommentId(Long userId, Long commentId);
    long countByCommentId(Long commentId);

}
