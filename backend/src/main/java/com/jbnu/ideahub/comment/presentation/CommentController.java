package com.jbnu.ideahub.comment.presentation;

import com.jbnu.ideahub.comment.dto.request.CommentCreateRequest;
import com.jbnu.ideahub.comment.dto.request.CommentUpdateRequest;
import com.jbnu.ideahub.comment.dto.response.CommentResponse;
import com.jbnu.ideahub.comment.service.CommentService;
import com.jbnu.ideahub.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(
            @RequestBody @Valid final CommentCreateRequest commentCreateRequest
    ) {
        final Long commentId = commentService.save(commentCreateRequest);
        return ResponseEntity.created(URI.create("/comments/" + commentId)).build();
    }

    @GetMapping("/entries/{entryId}/comments")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getCommentsByEntryId(
            @PathVariable final Long entryId
    ) {
        final List<CommentResponse> commentResponses = commentService.findAllByEntryId(entryId);
        return ResponseEntity.ok().body(new ApiResponse<>(commentResponses));
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable final Long commentId,
            @RequestBody @Valid final CommentUpdateRequest commentUpdateRequest
    ) {
        commentService.update(commentId, commentUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable final Long commentId
    ) {
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }
}
