package com.example.backend.controller;
import com.example.backend.Dto.LoginRequest;
import com.example.backend.Dto.SignupRequest;
import com.example.backend.services.StudentService;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;  // ✅ correct
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    @Autowired
    private StudentService service;


    //Signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {

        return service.signup(signupRequest);
    }


    /// /  LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        return service.login(loginRequest);
    }


    //GetOnly one using Id
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {

        return service.getStudentById(id);
    }


    //Get All Student
    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        return service.getAllStudents();
    }


    // Get User Profile
    @GetMapping("/test/{username}")
    public ResponseEntity<?> getMyProfile(@PathVariable String username) {
        return  service.getMyProfile(username);

    }
}
