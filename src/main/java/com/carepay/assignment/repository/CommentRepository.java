package com.carepay.assignment.repository;


import com.carepay.assignment.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query(
            value = "SELECT * FROM comment c WHERE c.post_id = :postId",
            nativeQuery = true)
    List<Comment> listByPostId(@Param("postId") Long postId);


    @Query(
            value = "SELECT * FROM comment c WHERE c.post_id = :postId and c.id=:commentId",
            nativeQuery = true)
    Comment findByPostId(@Param("postId") Long postId, @Param("commentId") Long commentId);
}
