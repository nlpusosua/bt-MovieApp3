package com.example.movieapp.service;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.repository.BlogResponsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogResponsitory blogResponsitory;
    public Page<Blog> getBlogByName( Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return blogResponsitory.findByStatusOrderByCreatedAtDesc( status, pageRequest);
    }

    public List<Blog> getByIdAndSlugAndStatus(int id, String slug, boolean status) {
        return blogResponsitory.findByIdAndSlugAndStatus(id, slug, status);
    }




}
