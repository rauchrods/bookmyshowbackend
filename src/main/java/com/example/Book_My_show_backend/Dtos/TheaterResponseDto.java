package com.example.Book_My_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TheaterResponseDto {

    private String name;

    private String city;

    private String address;
}
