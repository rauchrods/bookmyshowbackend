package com.example.Book_My_show_backend.Controllers;

import com.example.Book_My_show_backend.Dtos.MovieRequestDto;
import com.example.Book_My_show_backend.Models.MovieEntity;
import com.example.Book_My_show_backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
@Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody()MovieRequestDto movieRequestDto){
       return movieService.addMovie(movieRequestDto);
    }

    //findby name
    @GetMapping("/findByName")
    public MovieEntity findByName(@RequestParam("moviename") String moviename){
        return movieService.findByName(moviename);
    }


    //findall
    @GetMapping("/findall")
    public List<MovieEntity> findAll(){
        return movieService.findAll();
    }

}
