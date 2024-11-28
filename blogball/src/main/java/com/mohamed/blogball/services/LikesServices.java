package com.mohamed.blogball.services;

public interface LikesServices {
    void likeOrUnlikePost(Long userId, Long postId);
    void likeOrUnlikeComment(Long userId, Long commentId);
    long getPostLikeCount(Long postId);
    long getCommentLikeCount(Long commentId);
}
