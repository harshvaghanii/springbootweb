package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
