package com.example.backend.controller;

import com.example.backend.Dto.UserInfoDto;
import com.example.backend.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/student")
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    @PostMapping("/contact")
    public ResponseEntity<?> saveUser(@RequestBody UserInfoDto userInfoDto){

        return service.saveUser(userInfoDto);
    }

    /// All users
    @GetMapping("allusers")
    public ResponseEntity<?> getAllUsers(){
        return service.getAllUsers();

    }

}
