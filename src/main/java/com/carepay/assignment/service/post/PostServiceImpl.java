package com.carepay.assignment.service.post;

import javax.validation.Valid;

import com.carepay.assignment.domain.GeneralResponse;
import com.carepay.assignment.domain.comment.CommentDetails;
import com.carepay.assignment.domain.post.CreatePostRequest;
import com.carepay.assignment.domain.post.Post;
import com.carepay.assignment.domain.post.PostDetails;
import com.carepay.assignment.domain.post.PostInfo;
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
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    PostDetails postDetails;
    Post post;
    PostInfo postInfo;
    GeneralResponse generalResponse;


    @Override
    public PostDetails createPost(@Valid CreatePostRequest createPostRequest) {
       postDetails= new PostDetails();
        post =new Post();
       post.setTitle(createPostRequest.getTitle());
       post.setContent(createPostRequest.getContent());
        postDetails.setContent(createPostRequest.getContent());
       postRepository.saveAndFlush(post);
       return postDetails;
    }

    @Override
    public Page<PostInfo> getPosts(Pageable pageable) {
     List<Post> posts=  postRepository.findAll();
     List<PostInfo> postList= new ArrayList<>(posts.size());
    for(int i=0;i<posts.size();i++){
        postInfo= new PostInfo();
        postInfo.setId(posts.get(i).getId());
        postInfo.setTitle(posts.get(i).getTitle());
        postList.add(postInfo);


     }

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), postList.size());
        final Page<PostInfo> page = new PageImpl<>(postList.subList(start, end), pageable, postList.size());

     return page;

    }

    @Override
    public Object getPostDetails(Long id) {

       generalResponse=new GeneralResponse();
        try{
            postDetails =new PostDetails();
            postDetails.setContent(postRepository.findById(id).get().getContent());
            return postDetails;

        }catch (Exception e){
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Post Not Found");
            return generalResponse;
        }

    }

    @Override
    public GeneralResponse deletePost(Long id) {
        generalResponse=new GeneralResponse();
        try{
            postRepository.deleteById(id);
            generalResponse.setDescription("Post Deleted Successfully");
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            return generalResponse;
        }catch (Exception e){
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Post Not Found");
            return generalResponse;
        }



    }
}
