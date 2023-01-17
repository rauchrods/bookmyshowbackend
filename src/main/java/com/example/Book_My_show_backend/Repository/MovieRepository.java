package com.example.Book_My_show_backend.Repository;

import com.example.Book_My_show_backend.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    MovieEntity findByMoviename(String moviename);
}
