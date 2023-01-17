package com.example.Book_My_show_backend.Service;

import com.example.Book_My_show_backend.Dtos.TheaterRequestDto;
import com.example.Book_My_show_backend.Enums.SeatType;
import com.example.Book_My_show_backend.Models.TheaterEntity;
import com.example.Book_My_show_backend.Models.TheaterSeatEntity;
import com.example.Book_My_show_backend.Repository.TheaterRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(TheaterRequestDto theaterRequestDto){
        TheaterEntity theater = TheaterEntity.builder().name(theaterRequestDto.getName()).city(theaterRequestDto.getCity()).
                address(theaterRequestDto.getAddress()).build();
        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterseat(theater);

        theater.setTheaterSeatEntityList(theaterSeatEntityList);

        theaterRepository.save(theater);

        return "Succesfully added a Theater";
    }

    public List<TheaterSeatEntity> createTheaterseat(TheaterEntity theater){

        List<TheaterSeatEntity> theaterSeatEntityList  = new ArrayList<>();

        for(int i=1;i<=15;i++){
            char ch = 'A';
            SeatType seatType=SeatType.CLASSIC;
            int rate=100;
            if(i>5 && i<=10){
                ch='B';
                seatType=SeatType.PLATINUM;
                rate=150;
            }
            else if(i>=11 && i<=15){
                ch='C';
                seatType=SeatType.PLATINUM;
                rate=200;
            }
            int lev=i%5;
            if(lev==0) lev=5;
            String str = Integer.toString(lev) + ch;
            TheaterSeatEntity theaterSeatEntity = new TheaterSeatEntity(str,seatType,rate);
            theaterSeatEntity.setTheater(theater);
            theaterSeatEntityList.add(theaterSeatEntity);
        }


        return theaterSeatEntityList;
    }
}
