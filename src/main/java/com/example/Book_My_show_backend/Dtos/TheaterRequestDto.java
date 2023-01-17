package com.example.Book_My_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TheaterRequestDto {

    private String name;

    private String city;

    private String address;
}
