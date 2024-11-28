package com.mohamed.blogball.controller;

import com.mohamed.blogball.services.LikesServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/posts")
@Tag(name = "likes")
public class LikesController {

    private final LikesServices likesServices;

    @PostMapping("/{postId}/like")
    public void likeOrUnlikePost(@RequestParam Long userId, @PathVariable Long postId) {
        likesServices.likeOrUnlikePost(userId, postId);
    }

    @PostMapping("/comments/{commentId}/like")
    public void likeOrUnlikeComment(@RequestParam Long userId, @PathVariable Long commentId) {
        likesServices.likeOrUnlikeComment(userId, commentId);
    }
}
