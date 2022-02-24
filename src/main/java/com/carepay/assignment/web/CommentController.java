package com.carepay.assignment.web;

import com.carepay.assignment.domain.comment.CommentDetails;
import com.carepay.assignment.domain.comment.CommentInfo;
import com.carepay.assignment.domain.comment.CreateCommentRequest;
import com.carepay.assignment.service.comment.CommentService;
import com.carepay.assignment.service.comment.CommentServiceImpl;
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
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }



    @GetMapping("/{id}/comments")
    Page<CommentInfo> getComments(@PathVariable("id") final Long idPost, Pageable pageable) {

        return commentService.getComments(idPost, pageable);
    }

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    CommentDetails createComment(@PathVariable("id") final Long idPost, @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        return commentService.createComment(idPost, createCommentRequest);
    }

    @GetMapping("/{id}/comments/{id}")
    Object getCommentDetails(@PathVariable("id") final Long idPost, @PathVariable("id") final Long idComment) {

        return commentService.getCommentDetails(idPost,idComment);
    }

    @DeleteMapping("/{id}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    Object deleteComment(@PathVariable("id") final Long idPost, @PathVariable("id") final Long idComment) {

        return commentService.deleteComment(idPost,idComment);
    }
}
