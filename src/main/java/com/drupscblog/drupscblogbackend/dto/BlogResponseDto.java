package com.drupscblog.drupscblogbackend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BlogResponseDto {
    private Integer id;
    private String content;
    private String author;
    private LocalDateTime localDateTime;
}
