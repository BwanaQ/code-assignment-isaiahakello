package com.carepay.assignment.service.comment;

import com.carepay.assignment.domain.GeneralResponse;
import com.carepay.assignment.domain.comment.CommentDetails;
import com.carepay.assignment.domain.comment.CommentInfo;
import com.carepay.assignment.domain.comment.CreateCommentRequest;
import com.carepay.assignment.domain.post.CreatePostRequest;
import com.carepay.assignment.domain.post.PostDetails;
import com.carepay.assignment.domain.post.PostInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface CommentService {
    CommentDetails createComment(Long idPost, @Valid CreateCommentRequest createCommentRequest);

    Page<CommentInfo> getComments(Long idPost, final Pageable pageable);

    Object getCommentDetails(Long idPost, Long id);

    GeneralResponse deleteComment(Long idPost, Long id);
}

