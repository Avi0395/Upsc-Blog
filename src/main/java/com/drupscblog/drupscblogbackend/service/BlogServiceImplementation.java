package com.drupscblog.drupscblogbackend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.drupscblog.drupscblogbackend.dto.BlogRequestDto;
import com.drupscblog.drupscblogbackend.dto.BlogResponseDto;
import com.drupscblog.drupscblogbackend.model.Blog;
import com.drupscblog.drupscblogbackend.repository.BlogRepository;


@Service
public class BlogServiceImplementation implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImplementation(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // logic for create new blog
    @Override
    public BlogResponseDto createNewBlog(BlogRequestDto blogDto) {
        if (blogDto == null || blogDto.getContent() == null || blogDto.getAuthor() == null) {
            throw new IllegalArgumentException("Blog data is incomplete...");
        }

        Blog blog = new Blog();
        blog.setContent(blogDto.getContent());
        blog.setAuthor(blogDto.getAuthor());

        // save into database
        Blog savedBlog = blogRepository.save(blog);

        BlogResponseDto blogResponseDto = new BlogResponseDto();
        BeanUtils.copyProperties(savedBlog, blogResponseDto);

        return blogResponseDto;
    }

    // logic for retrieve all blogs with pagination
    @Override
    public Page<BlogResponseDto> retrieveAllBlogs(int page, int size) {
        // fetch from database
        if (page < 0 || size <= 0) {
            throw new RuntimeException("Page index must be >= 0 and size must be > 0.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        Page<Blog> blogPage = blogRepository.findAll(pageable);

        return blogPage.map(blog -> {
            BlogResponseDto blogResponseDto = new BlogResponseDto();
            BeanUtils.copyProperties(blog, blogResponseDto);
            return blogResponseDto;
        });

    }

    // logic for retrieve single blog
    @Override
    public BlogResponseDto retrieveSingleBlog(Integer id) {
        // check and fetch from database
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id:" + id));

        BlogResponseDto blogResponseDto = new BlogResponseDto();
        BeanUtils.copyProperties(blog, blogResponseDto);

        return blogResponseDto;
    }

    // logic for update blog
    @Override
    public BlogResponseDto updateBlog(Integer id, BlogRequestDto blogRequestDto) {
        // check in database to update
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id:" + id + "to update"));

        if (blogRequestDto.getContent() != null) {
            blog.setContent(blogRequestDto.getContent());
        }

        if (blogRequestDto.getAuthor() != null) {
            blog.setAuthor(blogRequestDto.getAuthor());
        }

        Blog updateBlog = blogRepository.save(blog);

        BlogResponseDto blogResponseDto = new BlogResponseDto();
        BeanUtils.copyProperties(updateBlog, blogResponseDto);

        return blogResponseDto;
    }

    // logic for delete blog
    @Override
    public String deleteBlog(Integer id) {
        // check for blog exists in database
        if (!blogRepository.existsById(id)) {
            throw new RuntimeException("Blog with Id:" + id + "not found to delete");
        }

        // deletes from database
        blogRepository.deleteById(id);

        return "Blog deleted successfully...";
    }

    @Override
    public String generateSummary(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateSummary'");
    }

}
