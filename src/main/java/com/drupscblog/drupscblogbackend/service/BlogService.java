package com.drupscblog.drupscblogbackend.service;

import org.springframework.data.domain.Page;

import com.drupscblog.drupscblogbackend.dto.BlogRequestDto;
import com.drupscblog.drupscblogbackend.dto.BlogResponseDto;

public interface BlogService {
    public BlogResponseDto createNewBlog(BlogRequestDto blogDto);

    public Page<BlogResponseDto> retrieveAllBlogs(int page, int size);

    public BlogResponseDto retrieveSingleBlog(Integer id);

    public BlogResponseDto updateBlog(Integer id, BlogRequestDto blogRequestDto);

    public String deleteBlog(Integer id);

}
