package com.example.Book_My_show_backend.Repository;

import com.example.Book_My_show_backend.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {
}
