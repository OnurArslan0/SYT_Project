package com.SYT.STY_project.repository;
import com.SYT.STY_project.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findByName(String moveId);

    Movie save(Movie movie);

    List<Movie> findAll();
}
