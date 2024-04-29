package com.SYT.STY_project.service;

import com.SYT.STY_project.dto.RateDTO;
import com.SYT.STY_project.model.Rate;

import java.util.List;

public interface RateService {

    boolean getRatingBoolByMovieUser(String movieId, String userName);

    Rate getRatingByMovieUser(String movieId, String userName);
    boolean addRating(String movieId,RateDTO rateDTO);

    boolean patchRating(String filmId, RateDTO rateDTO);

    public List<Rate> getFilmRatings(String movieId);
}
