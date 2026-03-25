package com.example.backend.services;

import com.example.backend.Dto.LoginRequest;
import com.example.backend.Dto.SignupRequest;
import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

/// Get All Students
   public ResponseEntity<?> getAllStudents(){
       return ResponseEntity.ok(
               Map.of(
                       "success",true,
                       "data",repository.findAll())
       );
   }

   //Get One Students///
    public ResponseEntity<?> getStudentById(Long id){
       Optional<Student> student= repository.findById(id);
       if (student.isEmpty()){
           return ResponseEntity.badRequest().body(
                   Map.of(
                           "success",false,
                           "message","Student not found"
                   )
           );
       }
       return ResponseEntity.ok(
               Map.of(
                       "success",true,
                       "data",student.get()
               )
       );
    }

    //Signup
    public ResponseEntity<?> signup(SignupRequest request){
        // check password match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password not match");
        }
        // check email exists
        if(repository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email already exists");
        }
        //Object of model/or
        Student student = new Student();
        student.setEmail(request.getEmail());
        student.setUsername(request.getUsername());

        // encrypt password
        student.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(student);
        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "User registered successfully"
                ));
    }

    //Login

    public  ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<Student> student1= repository.findByEmail(loginRequest.getEmail());
        if(student1.isEmpty()){
            return ResponseEntity.badRequest().body("User not Found");
        }
        Student student2 = student1.get();
        if(!passwordEncoder.matches(loginRequest.getPassword(),student2.getPassword())){
           return ResponseEntity.badRequest().body("Invalid password");
        }
        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Login Successful"));

    }
}
