package com.carepay.assignment.web;

import com.carepay.assignment.domain.dto.CommentDetails;
import com.carepay.assignment.domain.dto.CreateCommentRequest;
import com.carepay.assignment.domain.dto.CreatePostRequest;
import com.carepay.assignment.domain.dto.PostDetails;
import com.carepay.assignment.domain.dto.PostInfo;
import com.carepay.assignment.service.CommentService;
import com.carepay.assignment.service.PostService;
import com.carepay.assignment.service.PostServiceImpl;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(
        PostServiceImpl postService,
        CommentService commentService
    ) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    Page<PostInfo> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PostDetails createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        return postService.createPost(createPostRequest);
    }

    @GetMapping("{id}")
    PostDetails getPostDetails(@PathVariable("id") final Long id) {
        return postService.getPostDetails(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePost(@PathVariable("id") final Long id) {
        postService.deletePost(id);
    }

    @PostMapping("{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    CommentDetails deletePost(@PathVariable("postId") final Long postId,
        @Valid @RequestBody CreateCommentRequest request) {
        return commentService.createComment(postId, request);
    }

    @GetMapping("{postId}/comments")
    Page<CommentDetails> getPostComments(@PathVariable("postId") final Long postId, Pageable pageable) {
        return commentService.getComments(postId, pageable);
    }

    @GetMapping("{postId}/comments/{commentId}")
    CommentDetails getPostComment(
        @PathVariable("commentId") final Long commentId
    ) {
        return commentService.getCommentDetails(commentId);
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    void deletePostComment(
        @PathVariable("commentId") final Long commentId
    ) {
        commentService.deleteComment(commentId);
    }
}
