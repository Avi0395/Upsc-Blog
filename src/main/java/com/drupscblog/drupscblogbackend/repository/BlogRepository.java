package com.drupscblog.drupscblogbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drupscblog.drupscblogbackend.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

}