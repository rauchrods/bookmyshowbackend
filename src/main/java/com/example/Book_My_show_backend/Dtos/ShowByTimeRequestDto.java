package com.example.Book_My_show_backend.Dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowByTimeRequestDto {

    private LocalDate showdate;

    private LocalTime starttime;

    private LocalTime endtime;

    private int movieid;
}
