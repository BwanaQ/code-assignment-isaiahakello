package com.carepay.assignment.web;

import com.carepay.assignment.domain.post.CreatePostRequest;
import com.carepay.assignment.domain.post.PostDetails;
import com.carepay.assignment.domain.post.PostInfo;
import com.carepay.assignment.domain.comment.CommentDetails;
import com.carepay.assignment.domain.comment.CommentInfo;
import com.carepay.assignment.domain.comment.CreateCommentRequest;
import com.carepay.assignment.service.comment.CommentService;
import com.carepay.assignment.service.comment.CommentService;
import com.carepay.assignment.service.post.PostService;
import com.carepay.assignment.service.post.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    private final PostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
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
    Object getPostDetails(@PathVariable("id") final Long id) {

        return postService.getPostDetails(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    Object deletePost(@PathVariable("id") final Long id) {

        return postService.deletePost(id);
    }





}
