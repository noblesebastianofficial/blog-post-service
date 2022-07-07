package com.carepay.assignment.service;

import com.carepay.assignment.domain.dto.CreatePostRequest;
import com.carepay.assignment.domain.dto.PostDetails;
import com.carepay.assignment.domain.dto.PostInfo;
import com.carepay.assignment.domain.entities.Post;
import com.carepay.assignment.mapper.DataMapper;
import com.carepay.assignment.repository.PostRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @NonNull
    private final PostRepository postRepository;

    private final DataMapper dataMapper;


    @Override
    public PostDetails createPost(@Valid CreatePostRequest createPostRequest) {
        Post post  = postRepository.save(dataMapper.mapToPost(createPostRequest));
        return new PostDetails(post.getId(), post.getTitle(), post.getContent());
    }

    @Override
    public Page<PostInfo> getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(el -> new PostInfo(el.getId(), el.getTitle()));
    }

    @Override
    public PostDetails getPostDetails(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            throw new EntityNotFoundException(String.format("Post with id: %d not found", id));
        }
        Post post = optionalPost.get();
        return new PostDetails(post.getId(), post.getTitle(), post.getContent());
    }

    @Override
    public void deletePost(Long id) {
        getPostDetails(id);
        postRepository.deleteById(id);
    }
}
