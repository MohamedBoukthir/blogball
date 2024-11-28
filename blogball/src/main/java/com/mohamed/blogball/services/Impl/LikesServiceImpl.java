package com.mohamed.blogball.services.Impl;

import com.mohamed.blogball.Repositories.*;
import com.mohamed.blogball.model.Comment;
import com.mohamed.blogball.model.Post;
import com.mohamed.blogball.model.User;
import com.mohamed.blogball.model.likes.CommentLike;
import com.mohamed.blogball.model.likes.PostLike;
import com.mohamed.blogball.services.LikesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesServices {

    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void likeOrUnlikePost(Long userId, Long postId) {
        // Fetch the Post entity from the database by postId. Throw an exception if not found.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Fetch the User entity by userId. Throw an exception if not found.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has already liked the post.
        Optional<PostLike> existingLike = postLikeRepository.findByUserIdAndPostId(userId, postId);

        // If a like exists, remove it (unlike). Otherwise, add a new like.
        if (existingLike.isPresent()) {
            postLikeRepository.delete(existingLike.get());
        } else {
            PostLike postLike = PostLike.builder()
                    .user(user)  // Associate user
                    .post(post)  // Associate post
                    .build();
            // Save the new like to the database.
            postLikeRepository.save(postLike);
        }
    }

    @Override
    public void likeOrUnlikeComment(Long userId, Long commentId) {
        // Fetch the Comment entity from the database by commentId. Throw an exception if not found.
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Fetch the User entity by userId. Throw an exception if not found.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user has already liked the comment.
        Optional<CommentLike> existingLike = commentLikeRepository.findByUserIdAndCommentId(userId, commentId);

        // If a like exists, remove it (unlike). Otherwise, add a new like.
        if (existingLike.isPresent()) {
            commentLikeRepository.delete(existingLike.get());
        } else {
            // Create a new like for the comment.
            CommentLike commentLike = CommentLike.builder()
                    .user(user)         // Associate user
                    .comment(comment)   // Associate comment
                    .build();

            // Save the new like to the database.
            commentLikeRepository.save(commentLike);
        }
    }

    // Get Like Count for a Post
    @Override
    public long getPostLikeCount(Long postId) {
        return postLikeRepository.countByPostId(postId);
    }

    // Get Like Count for a Comment
    @Override
    public long getCommentLikeCount(Long commentId) {
        return commentLikeRepository.countByCommentId(commentId);
    }
}
