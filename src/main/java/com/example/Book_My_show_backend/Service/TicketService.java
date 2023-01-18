package com.example.Book_My_show_backend.Service;

import com.example.Book_My_show_backend.Dtos.BookTicketRequestDto;
import com.example.Book_My_show_backend.Models.ShowEntity;
import com.example.Book_My_show_backend.Models.ShowSeatEntity;
import com.example.Book_My_show_backend.Models.TicketEntity;
import com.example.Book_My_show_backend.Models.UserEntity;
import com.example.Book_My_show_backend.Repository.ShowRepository;
import com.example.Book_My_show_backend.Repository.ShowSeatRepository;
import com.example.Book_My_show_backend.Repository.TicketRepository;
import com.example.Book_My_show_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{

        List<String> requestedseats = bookTicketRequestDto.getRequestseats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowid()).get();

        UserEntity user = userRepository.findById(bookTicketRequestDto.getUserid()).get();

        List<ShowSeatEntity> showSeatEntityList = showEntity.getShowSeatEntityList();

        List<ShowSeatEntity> bookseats = new ArrayList<>();

        for (ShowSeatEntity showSeat : showSeatEntityList){

            if(showSeat.isBooked()==false && requestedseats.contains(showSeat.getSeatNo())){
                bookseats.add(showSeat);
            }

        }

        if(bookseats.size()!=requestedseats.size()){
            throw new Exception("All the seats are not available cannot book the tickets");
        }

        TicketEntity ticketEntity = new TicketEntity();

        double total_amount=0.0;
        double multiplier = showEntity.getMultiplier();
        String allotedseat="";

        for(ShowSeatEntity showSeat:bookseats){

            allotedseat=allotedseat + showSeat.getSeatNo() + ", ";
            showSeat.setBooked(true);
            showSeat.setBookeAt(new Date());
            showSeat.setTicket(ticketEntity);
            showSeat.setShow(showEntity);

            if(showSeat.getSeatNo().charAt(1)=='A'){
                total_amount= total_amount + 100*multiplier;
            }
            else {
                total_amount= total_amount + 200*multiplier;
            }

        }

        ticketEntity.setBookedAt(new Date());
        ticketEntity.setAmount((int) total_amount);
        ticketEntity.setUser(user);
        ticketEntity.setShow(showEntity);
        ticketEntity.setShowSeatEntityList(bookseats);
        ticketEntity.setAlloted_seats(allotedseat);

        List<TicketEntity> ticketEntityList = user.getTicketEntityList();
        ticketEntityList.add(ticketEntity);
        user.setTicketEntityList(ticketEntityList);

        List<TicketEntity> ticketEntityListshow = showEntity.getTicketEntityList();
        ticketEntityListshow.add(ticketEntity);
        showEntity.setTicketEntityList(ticketEntityListshow);

        ticketRepository.save(ticketEntity);

        return "Successfully created a ticket!";
    }
}
