package com.example.Book_My_show_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TicketResponseDto {

    private String alloted_seats;

    private int amount;

    private Date bookedAt;
}
