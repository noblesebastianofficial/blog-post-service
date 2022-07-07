package com.carepay.assignment.service;

import com.carepay.assignment.domain.dto.CreatePostRequest;
import com.carepay.assignment.domain.dto.PostDetails;
import com.carepay.assignment.domain.dto.PostInfo;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostDetails createPost(@Valid CreatePostRequest createPostRequest);

    Page<PostInfo> getPosts(final Pageable pageable);

    PostDetails getPostDetails(Long id);

    void deletePost(Long id);
}
