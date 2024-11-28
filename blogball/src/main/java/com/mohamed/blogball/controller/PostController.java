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
@RequestMapping("posts")
@Tag(name = "posts")
public class PostController {

    private final LikesServices likesServices;

    @GetMapping("/{postId}/like-count")
    public ResponseEntity<Long> getPostLikeCount(@PathVariable Long postId) {
        long likeCount = likesServices.getPostLikeCount(postId);
        return ResponseEntity.ok(likeCount);
    }

}
