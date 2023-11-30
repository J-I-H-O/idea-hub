package com.jbnu.ideahub.comment.application;

import com.jbnu.ideahub.comment.dto.request.CommentCreateRequest;
import com.jbnu.ideahub.comment.dto.request.CommentUpdateRequest;
import com.jbnu.ideahub.comment.dto.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    // TODO
    public Long save(CommentCreateRequest commentCreateRequest) {
        return 1L;
    }

    public List<CommentResponse> findAllByEntryId(Long entryId) {

        return null;
    }

    public void update(Long commentId, CommentUpdateRequest commentUpdateRequest) {

    }

    public void delete(Long commentId) {

    }
}
