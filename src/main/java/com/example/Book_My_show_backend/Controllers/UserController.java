package com.example.Book_My_show_backend.Controllers;

import com.example.Book_My_show_backend.Dtos.UserRequestDto;
import com.example.Book_My_show_backend.Models.UserEntity;
import com.example.Book_My_show_backend.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    Userservice userservice;

    @PostMapping("/add")
    public String addUser(@RequestBody()UserRequestDto userRequestDto){

       return userservice.addUser(userRequestDto);
    }

    @GetMapping("/findByName")
    public List<UserEntity> getuserByName(@RequestParam("username") String username){
        return userservice.getuserByName(username);
    }

    @GetMapping("/findAll")
    public List<UserEntity> findallUsers(){
        return userservice.findallUsers();
    }
}
