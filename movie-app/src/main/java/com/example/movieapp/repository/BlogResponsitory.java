package com.example.movieapp.repository;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogResponsitory extends JpaRepository<Blog, Integer> {
    Page<Blog> findByStatusOrderByCreatedAtDesc(Boolean status, Pageable pageable);
    List<Blog> findByIdAndSlugAndStatus(Integer id, String slug, boolean status);
}
