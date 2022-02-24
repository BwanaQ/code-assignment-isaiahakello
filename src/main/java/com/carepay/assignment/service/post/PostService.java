package com.carepay.assignment.service.post;

import javax.validation.Valid;

import com.carepay.assignment.domain.GeneralResponse;
import com.carepay.assignment.domain.post.CreatePostRequest;
import com.carepay.assignment.domain.post.PostDetails;
import com.carepay.assignment.domain.post.PostInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    PostDetails createPost(@Valid CreatePostRequest createPostRequest);

    Page<PostInfo> getPosts(final Pageable pageable);

    Object getPostDetails(Long id);

    GeneralResponse deletePost(Long id);

}
