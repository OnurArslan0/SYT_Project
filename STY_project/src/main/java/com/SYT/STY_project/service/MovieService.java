package com.SYT.STY_project.service;

import com.SYT.STY_project.dto.RateDTO;
import com.SYT.STY_project.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieService {


    Movie getMovieByName(String name);

    List<Movie> getAllMovies();

}
