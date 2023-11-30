package com.jbnu.ideahub.like.presentation;

import com.jbnu.ideahub.like.dto.request.LikeRequest;
import com.jbnu.ideahub.like.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> createLike(
            @RequestBody @Valid final LikeRequest likeRequest
    ) {
        likeService.save(likeRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLike(
            @RequestBody @Valid final LikeRequest likeRequest
    ) {
        likeService.delete(likeRequest);
        return ResponseEntity.noContent().build();
    }
}
