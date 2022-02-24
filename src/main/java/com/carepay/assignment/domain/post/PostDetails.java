package com.carepay.assignment.domain.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include. NON_NULL)
public class PostDetails extends PostInfo {
    private String content;
}
