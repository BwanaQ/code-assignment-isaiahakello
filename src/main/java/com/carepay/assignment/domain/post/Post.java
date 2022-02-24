package com.carepay.assignment.domain.post;

import com.carepay.assignment.domain.comment.Comment;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

    @OneToMany(targetEntity = Comment.class, mappedBy = "postId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comment;
}
