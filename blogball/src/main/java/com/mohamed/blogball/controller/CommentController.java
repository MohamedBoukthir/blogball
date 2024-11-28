package com.mohamed.blogball.controller;

import com.mohamed.blogball.services.LikesServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
@Tag(name = "comments")
public class CommentController {

    private final LikesServices likesServices;

    @GetMapping("/{commentId}/like-count")
    public ResponseEntity<Long> getCommentLikeCount(@PathVariable Long commentId) {
        long likeCount = likesServices.getCommentLikeCount(commentId);
        return ResponseEntity.ok(likeCount);
    }

}
