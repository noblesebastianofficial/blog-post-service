package com.carepay.assignment.service;

import com.carepay.assignment.domain.dto.CommentDetails;
import com.carepay.assignment.domain.dto.CreateCommentRequest;
import com.carepay.assignment.domain.entities.Comment;
import com.carepay.assignment.domain.entities.Post;
import com.carepay.assignment.repository.CommentRepository;
import com.carepay.assignment.repository.PostRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    @NonNull
    private final CommentRepository commentRepository;
    @NonNull
    private final PostRepository postRepository;


    @Override
    public CommentDetails createComment(Long postId, CreateCommentRequest createCommentRequest) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (!optionalPost.isPresent()) {
            throw new EntityNotFoundException(String.format("Post with id: %d not found", postId));
        }
        Post post = optionalPost.get();
        Comment comment = commentRepository.save(new Comment(null, post, createCommentRequest.getComment()));
        return new CommentDetails(comment.getId(), postId, comment.getComment());
    }

    @Override
    public Page<CommentDetails> getComments(Long postId, Pageable pageable) {
        Page<Comment> all = commentRepository.findByPostId(postId, pageable);
        return all.map(it -> new CommentDetails(it.getId(), it.getPost().getId(), it.getComment()));
    }

    @Override
    public CommentDetails getCommentDetails(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent()) {
            throw new EntityNotFoundException("Comment not found");
        }
        Comment comment = optionalComment.get();
        return new CommentDetails(comment.getId(), comment.getPost().getId(), comment.getComment());
    }

    @Override
    public void deleteComment(Long commentId) {
        getCommentDetails(commentId);
        commentRepository.deleteById(commentId);
    }
}
