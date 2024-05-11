package com.example.movieapp.controller;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.service.BlogService;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final BlogService blogService;

    @GetMapping("/")
    public String getHomePage(Model model) {
//        demo cách chọn phim mới, làm tương tự với phim hot,...
        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1,6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_LE, true, 1,6).getContent();
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, 1,6).getContent();
        model.addAttribute("listPhimle", listPhimLe);
        model.addAttribute("listPhimbo", listPhimBo);
        model.addAttribute("listPhimChieuRap", listPhimChieuRap);
        return "web/index";
    }



    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(Model model) {
        List<Movie> movies = movieService.getMoviesByType(MovieType.PHIM_LE, true, Sort.by("createdAt").descending());
        model.addAttribute("movies", movies);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model) {
        List<Movie> movies = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, Sort.by("createdAt").descending());
        model.addAttribute("movies", movies);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/tin-tuc")
    public String getTinTuc(Model model ,@RequestParam(required = false, defaultValue = "1") int page,
    @RequestParam(required = false, defaultValue = "12") int pageSize){
        Page<Blog> pageBlog =blogService.getBlogByName( true, page, pageSize);
        model.addAttribute("pageBlog", pageBlog);
        model.addAttribute("currentPage", page);
        return"web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String getChitiettintuc(Model model, @PathVariable int id, @PathVariable String slug) {
        List<Blog> blog = blogService.getByIdAndSlugAndStatus(id, slug, true);
        model.addAttribute("blog", blog);
        return "web/chi-tiet-tin-tuc";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getChiTietMovie(Model model, @PathVariable("id") int id, @PathVariable("slug") String slug) {
        Movie movie = movieService.getMovie(id, slug, true);
        model.addAttribute("movie", movie);
        return "web/chi-tiet-phim";
    }
}

