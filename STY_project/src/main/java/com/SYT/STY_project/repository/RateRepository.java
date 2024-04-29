package com.SYT.STY_project.repository;

import com.SYT.STY_project.dto.RateDTO;
import com.SYT.STY_project.model.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RateRepository extends MongoRepository<Rate, String> {

    Rate save(Rate rate);

    Rate deleteByMovieIdAndUserName(String movieId,String userName);

    List<Rate> findByMovieId(String movieId);

    boolean existsByMovieIdAndUserName(String movieId, String userName);

    Rate getRateByMovieIdAndUserName(String movieId, String userName);

}
