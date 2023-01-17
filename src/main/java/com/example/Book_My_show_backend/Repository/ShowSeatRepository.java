package com.example.Book_My_show_backend.Repository;

import com.example.Book_My_show_backend.Models.ShowSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeatEntity,Integer> {
}
