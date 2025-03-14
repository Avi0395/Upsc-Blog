package com.drupscblog.drupscblogbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drupscblog.drupscblogbackend.dto.BlogRequestDto;
import com.drupscblog.drupscblogbackend.dto.BlogResponseDto;
import com.drupscblog.drupscblogbackend.service.BlogService;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // post request to create blog
    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody BlogRequestDto blogRequestDto) {
        try {
            BlogResponseDto createdBlog = blogService.createNewBlog(blogRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpeccted error occurred");
        }
    }

    // get request for fetching all blogs with pagination
    @GetMapping
    public ResponseEntity<?> retrieveAllBlogs(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        try {
            Page<BlogResponseDto> blogPage = blogService.retrieveAllBlogs(page, size);
            return ResponseEntity.ok(blogPage);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again.");
        }
    }

    // get request for fetch single blog with id
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveSingleBlog(@PathVariable Integer id) {
        try {
            BlogResponseDto retrievedBlog = blogService.retrieveSingleBlog(id);
            return ResponseEntity.ok(retrievedBlog);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again.");
        }
    }

    // update request for existing blog with id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Integer id, @RequestBody BlogRequestDto blogRequestDto) {
        try {
            BlogResponseDto updatedBlog = blogService.updateBlog(id, blogRequestDto);
            return ResponseEntity.ok(updatedBlog);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again.");
        }
    }

    // delete request for existing blog with id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        try {
            String message = blogService.deleteBlog(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again.");
        }
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<?> generateSummary(@PathVariable Integer id) {
        try {
            String summary = blogService.generateSummary(id);
            return ResponseEntity.ok(summary);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again.");
        }
    }
}
