package com.example.Book_My_show_backend.Service;

import com.example.Book_My_show_backend.Dtos.UserRequestDto;
import com.example.Book_My_show_backend.Models.UserEntity;
import com.example.Book_My_show_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class Userservice {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserRequestDto userRequestDto){

        UserEntity user = UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();

        try {
            userRepository.save(user);
        }
        catch (Exception e){
            return "Error User cannot be added";
        }

        return "Successfully added a user!";
    }

    public List<UserEntity> getuserByName(String username){

        List<UserEntity> userEntityList = userRepository.findByName(username);

        return  userEntityList;
    }


    public List<UserEntity> findallUsers(){

        List<UserEntity> userEntityList= userRepository.findAll();

        return userEntityList;
    }
}
