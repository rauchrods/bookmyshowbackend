package com.example.Book_My_show_backend.Service;

import com.example.Book_My_show_backend.Dtos.MovieRequestDto;
import com.example.Book_My_show_backend.Models.MovieEntity;
import com.example.Book_My_show_backend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieRequestDto movieRequestDto){

        MovieEntity movie = MovieEntity.builder().moviename(movieRequestDto.getMoviename()).releasedate(movieRequestDto.getReleasedate())
                .duration(movieRequestDto.getDuration()).build();

        movieRepository.save(movie);

        return "Movie Succesfully added!";
    }


    public MovieEntity findByName(String moviename) {
        MovieEntity movie = null;
        try {
            movie = movieRepository.findByMoviename(moviename);
        } catch (Exception e) {
            System.out.println("Movie does not exist in db");
        }
        return movie;
    }

    public List<MovieEntity> findAll(){
         List<MovieEntity> movieEntityList = movieRepository.findAll();
         return  movieEntityList;
    }
}
