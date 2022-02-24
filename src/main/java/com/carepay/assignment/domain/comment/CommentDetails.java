package com.carepay.assignment.domain.comment;

import com.carepay.assignment.domain.post.PostInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDetails  {
    private String content;
}
