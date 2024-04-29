package com.SYT.STY_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection  = "Films")
public class Movie {
    private String name;
    private String photoURL;
    private String description;
    private String directorName;
    @Id
    private String movieId;

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getDescription() {
        return description;
    }

    public String getDirectorName() {
        return directorName;
    }



}
