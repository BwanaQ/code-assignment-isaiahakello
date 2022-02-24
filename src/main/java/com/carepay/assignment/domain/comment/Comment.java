package com.carepay.assignment.domain.comment;

import com.carepay.assignment.domain.post.Post;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;
    private String comment;
}
