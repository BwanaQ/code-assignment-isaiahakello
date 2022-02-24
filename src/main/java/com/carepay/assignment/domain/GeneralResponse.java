package com.carepay.assignment.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GeneralResponse {
   public HttpStatus status;
   public String description;
}
