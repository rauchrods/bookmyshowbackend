package com.example.Book_My_show_backend.Service;

import com.example.Book_My_show_backend.Dtos.ShowByTimeRequestDto;
import com.example.Book_My_show_backend.Dtos.ShowRequestDto;
import com.example.Book_My_show_backend.Dtos.ShowResponseDto;
import com.example.Book_My_show_backend.Models.*;
import com.example.Book_My_show_backend.Repository.MovieRepository;
import com.example.Book_My_show_backend.Repository.ShowRepository;
import com.example.Book_My_show_backend.Repository.ShowSeatRepository;
import com.example.Book_My_show_backend.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    public String add(ShowRequestDto showRequestDto){

        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).
                showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        MovieEntity movie = movieRepository.findByMoviename(showRequestDto.getMoviename());

        TheaterEntity theater = theaterRepository.findById(showRequestDto.getTheaterid()).get();
        showEntity.setMovie(movie);
        showEntity.setTheater(theater);

        List<ShowSeatEntity> showSeatEntityList = createShowSeats(theater.getTheaterSeatEntityList());

        for(ShowSeatEntity showSeat: showSeatEntityList){
            showSeat.setShow(showEntity);
        }
        showEntity.setShowSeatEntityList(showSeatEntityList);



        List<ShowEntity> showEntityList = movie.getShowEntityList();
        showEntityList.add(showEntity);
        movie.setShowEntityList(showEntityList);


        List<ShowEntity> showEntityListt = theater.getShowEntityList();
        showEntityListt.add(showEntity);
        theater.setShowEntityList(showEntityListt);

        movieRepository.save(movie);
        theaterRepository.save(theater);

//      showRepository.save(showEntity);

      return "Show Succesfully added";
    }

    public List<ShowSeatEntity>  createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){

        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        for(TheaterSeatEntity theaterSeat: theaterSeatEntityList){
            ShowSeatEntity showSeatEntity = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatno())
                    .seatType(theaterSeat.getSeatType()).build();
            showSeatEntityList.add(showSeatEntity);
        }
        showSeatRepository.saveAll(showSeatEntityList);
        return  showSeatEntityList;
    }

    public List<ShowResponseDto> findshowsBymovieidandtime(ShowByTimeRequestDto showByTimeRequestDto){

       MovieEntity movie = movieRepository.findById(showByTimeRequestDto.getMovieid()).get();
       List<ShowEntity> showEntityList = movie.getShowEntityList();
       List<ShowResponseDto> showResponseDtoList  =new ArrayList<>();

       for(ShowEntity showEntity: showEntityList){
         if(showEntity.getShowDate().compareTo(showByTimeRequestDto.getShowdate())==0 && showEntity.getShowTime().compareTo(showByTimeRequestDto.getStarttime())==1
                       && showEntity.getShowTime().compareTo(showByTimeRequestDto.getEndtime())==-1){
             ShowResponseDto showResponseDto = ShowResponseDto.builder().showDate(showEntity.getShowDate())
                     .showTime(showEntity.getShowTime()).build();
             showResponseDtoList.add(showResponseDto);
         }
       }
       return showResponseDtoList;
    }
}