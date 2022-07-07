package com.carepay.assignment.service;

import com.carepay.assignment.domain.dto.CommentDetails;
import com.carepay.assignment.domain.dto.CreateCommentRequest;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommentService {

    CommentDetails createComment(Long postId, @Valid CreateCommentRequest createCommentRequest);

    Page<CommentDetails> getComments(Long postId, final Pageable pageable);

    CommentDetails getCommentDetails(Long commentId);

    void deleteComment(Long commentId);
}
