package com.carepay.assignment.domain.comment;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateCommentRequest {
    private Long postId;
    private String comment;
}
