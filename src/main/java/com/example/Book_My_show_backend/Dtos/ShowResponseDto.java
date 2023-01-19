package com.example.Book_My_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
public class ShowResponseDto {

    private LocalDate showDate;

    private LocalTime showTime;
}
