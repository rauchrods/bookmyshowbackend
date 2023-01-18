package com.example.Book_My_show_backend.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequestDto {

    private List<String> requestseats;

    private int showid;

    private int userid;
}
