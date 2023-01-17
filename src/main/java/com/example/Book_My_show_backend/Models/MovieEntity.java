package com.example.Book_My_show_backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String moviename;

    private Date releasedate;

    private int duration;

    //List of shows

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList;
}
