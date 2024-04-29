package com.SYT.STY_project.service.impl;

import com.SYT.STY_project.dto.RateDTO;
import com.SYT.STY_project.model.Rate;
import com.SYT.STY_project.repository.RateRepository;
import com.SYT.STY_project.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository rateRepository;

    @Override
    public boolean getRatingBoolByMovieUser(String movieId, String userName) {
        return rateRepository.existsByMovieIdAndUserName(movieId,userName);
    }
    @Override
    public boolean addRating(String movieId,RateDTO rateDTO) {
        Rate rate = new Rate(movieId, rateDTO.getUserName(), rateDTO.getRate());
        try {
            rateRepository.save(rate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Rate> getFilmRatings(String movieId) {
        return rateRepository.findByMovieId(movieId);
    }

    @Override
    public boolean patchRating(String movieId, RateDTO rateDTO) {
        try {
            rateRepository.deleteByMovieIdAndUserName(movieId, rateDTO.getUserName());
            Rate newRate = new Rate(movieId, rateDTO.getUserName(), rateDTO.getRate());
            rateRepository.save(newRate);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public Rate getRatingByMovieUser(String movieId, String userName) {
        return rateRepository.getRateByMovieIdAndUserName(movieId,userName);
    }
}
