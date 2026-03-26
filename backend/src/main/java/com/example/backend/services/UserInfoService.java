package com.example.backend.services;

import com.example.backend.Dto.ApiResponse;
import com.example.backend.Dto.UserInfoDto;
import com.example.backend.model.UserInfo;
import com.example.backend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository repository;


    public ResponseEntity<?> saveUser(UserInfoDto req) {
        if (repository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        UserInfo user = new UserInfo();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setMobileNo(req.getMobileNo());
        user.setFeedback(req.getFeedback());

        repository.save(user);

        return ResponseEntity.ok(
               new ApiResponse(true,"Form submit successful",null));

    }

    /// ///////Get All User
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(
               new ApiResponse(true,"All Users",repository.findAll())
        );
    }
}