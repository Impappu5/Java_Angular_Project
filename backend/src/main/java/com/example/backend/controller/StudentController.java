package com.example.backend.controller;


import com.example.backend.Dto.LoginRequest;
import com.example.backend.Dto.SignupRequest;
import com.example.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    @Autowired
   private StudentService service;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        return service.signup(signupRequest);
    }



    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

       return service.login(loginRequest);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAll(){
        return service.getAllStudents();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id ){
        return service.getStudentById(id);
    }
}
