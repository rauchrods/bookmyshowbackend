package com.example.Book_My_show_backend.Controllers;

import com.example.Book_My_show_backend.Dtos.ShowByTimeRequestDto;
import com.example.Book_My_show_backend.Dtos.ShowRequestDto;
import com.example.Book_My_show_backend.Dtos.ShowResponseDto;
import com.example.Book_My_show_backend.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public String add(@RequestBody()ShowRequestDto showRequestDto){
     return  showService.add(showRequestDto);
    }

    @GetMapping("showsByMovieIdandtime")

    public List<ShowResponseDto> findshowsBymovieidandtime(@RequestBody()ShowByTimeRequestDto showByTimeRequestDto){
        return showService.findshowsBymovieidandtime(showByTimeRequestDto);
    }
}
