package com.carepay.assignment.service.comment;

import com.carepay.assignment.domain.GeneralResponse;
import com.carepay.assignment.domain.comment.Comment;
import com.carepay.assignment.domain.comment.CommentDetails;
import com.carepay.assignment.domain.comment.CommentInfo;
import com.carepay.assignment.domain.comment.CreateCommentRequest;
import com.carepay.assignment.domain.post.Post;
import com.carepay.assignment.domain.post.PostInfo;
import com.carepay.assignment.repository.CommentRepository;
import com.carepay.assignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl  implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    Comment comment;
    GeneralResponse generalResponse;
    CommentDetails commentDetails;
    CommentInfo commentInfo;

    @Override
    public CommentDetails createComment(Long idPost, CreateCommentRequest createCommentRequest) {
        commentDetails = new  CommentDetails();
        comment= new Comment();
        comment.setPostId(postRepository.findById(idPost).get());
        comment.setComment(createCommentRequest.getComment());
        commentRepository.save(comment);
        commentDetails.setContent(createCommentRequest.getComment());

        return commentDetails;
    }

    @Override
    public Page<CommentInfo> getComments(Long idPost, Pageable pageable) {
        //getting comments per post
        List<Comment> comments=  commentRepository.listByPostId(idPost);
        List<CommentInfo> commentList= new ArrayList<>(comments.size());
        for(int i=0;i<comments.size();i++){
            commentInfo= new CommentInfo();
            commentInfo.setId(comments.get(i).getId());
            commentInfo.setComment(comments.get(i).getComment());
            commentList.add(commentInfo);


        }

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), commentList.size());
        final Page<CommentInfo> page = new PageImpl<>(commentList.subList(start, end), pageable, commentList.size());

        return page;
    }

    @Override
    public Object getCommentDetails(Long idPost, Long id) {
        generalResponse=new GeneralResponse();
        try{
            commentDetails= new CommentDetails();
            commentDetails.setContent(commentRepository.findById(id).get().getComment());
            return commentDetails;

        }catch (Exception e){
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Comment Not Found");
            return generalResponse;
        }
    }

    @Override
    public GeneralResponse deleteComment(Long idPost, Long id) {
        generalResponse=new GeneralResponse();
        
        try{
            // setting up general response
            commentRepository.deleteById(id);
           generalResponse.setDescription("Comment Deleted Successfully");
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            return generalResponse;
        }catch (Exception e){
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Comment Not Found");
            return generalResponse;
        }



    }
}
