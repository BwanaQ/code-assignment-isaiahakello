package com.carepay.assignment.domain.post;


import lombok.*;

@Getter
@Setter
public class CreatePostRequest {
    private String title;
    private String content;
}
