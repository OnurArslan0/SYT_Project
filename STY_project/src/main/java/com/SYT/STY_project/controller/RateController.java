package com.SYT.STY_project.controller;

import com.SYT.STY_project.dto.RateDTO;
import com.SYT.STY_project.model.Rate;
import com.SYT.STY_project.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rates")
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Rate>> getFilmRatings(@PathVariable String movieId) {
        List<Rate> ratings = rateService.getFilmRatings(movieId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{movieId}/{userName}")
    public boolean getRatingBoolByMovieUser(@PathVariable String movieId,@PathVariable String userName) {
        return rateService.getRatingBoolByMovieUser(movieId,userName);
    }

    @PostMapping("/{movieId}/addRate")
    public ResponseEntity<String> addRate(@PathVariable String movieId, @RequestBody RateDTO rateDTO) {
        boolean ratingAdded = rateService.addRating(movieId, rateDTO);
        if (ratingAdded) {
            return ResponseEntity.ok("Puanlama başarıyla eklendi.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Puanlama eklenirken bir hata oluştu.");
        }
    }

    @PatchMapping("/{movieId}/patchRate")
    public ResponseEntity<String> patchRate(@PathVariable String movieId, @RequestBody RateDTO rateDTO) {
        boolean ratingUpdated = rateService.patchRating(movieId, rateDTO);
        if (ratingUpdated) {
            return ResponseEntity.ok("Puanlama başarıyla güncellendi.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Puanlama güncellenirken bir hata oluştu.");
        }
    }
}
