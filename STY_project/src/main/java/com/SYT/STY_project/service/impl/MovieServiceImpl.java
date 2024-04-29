package com.SYT.STY_project.service.impl;

import com.SYT.STY_project.dto.RateDTO;
import com.SYT.STY_project.model.Movie;
import com.SYT.STY_project.model.Rate;
import com.SYT.STY_project.repository.MovieRepository;
import com.SYT.STY_project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    private double calculateAverageRating(Map<String, Integer> userRatings) {
        double totalRating = 0.0;
        for (int rating : userRatings.values()) {
            totalRating += rating;
        }
        return totalRating / userRatings.size();
    }

    @Override
    public Movie getMovieByName(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
