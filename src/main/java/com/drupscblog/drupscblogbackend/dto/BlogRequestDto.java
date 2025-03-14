package com.drupscblog.drupscblogbackend.dto;

import lombok.Data;

@Data
public class BlogRequestDto {
    private String content;
    private String author;
}