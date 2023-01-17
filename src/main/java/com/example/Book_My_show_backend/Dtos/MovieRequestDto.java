package com.example.Book_My_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MovieRequestDto {

    private String moviename;

    private Date releasedate;

    private int duration;

}
