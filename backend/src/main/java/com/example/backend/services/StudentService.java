package com.example.backend.services;

import com.example.backend.Dto.ApiResponse;
import com.example.backend.Dto.LoginRequest;
import com.example.backend.Dto.SignupRequest;
import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
         new ApiResponse(true," All Students",repository.findAll()));
   }

   //Get One Students Using Id//
    public ResponseEntity<?> getStudentById(Long id){
       Optional<Student> student= repository.findById(id);
       if (student.isEmpty()){
           return ResponseEntity.badRequest().body(
                 new ApiResponse(false,"Student Not Found",null)
           );
       }
       return ResponseEntity.ok(
              new ApiResponse(true,"Student",student.get())
       );
    }
/// Get One myprofile only
    public ResponseEntity<?>  getMyProfile(String username){
       Student student=repository.findByUsername(username).orElse(null);
       if (student !=null){
           return ResponseEntity.ok(student);
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   new ApiResponse(false,"User Not Found",null)
           );
       }
    }

    //Signup
    public ResponseEntity<?> signup(SignupRequest request){
        // check password match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse(false,"Password do not match",null)
            );
        }
        // check email exists
        if(repository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(
                    new ApiResponse(false,"Email already exists",null)
            );
        }
        //Object of model/or
        Student student = new Student();
        student.setEmail(request.getEmail());
        student.setUsername(request.getUsername());

        student.setRole("ROLE_STUDENT");


        // encrypt password
        student.setPassword(passwordEncoder.encode(request.getPassword()));


        repository.save(student);
        return ResponseEntity.ok(
                new ApiResponse(true,"User registered successfully",student));
    }

    //Login

    public  ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<Student> student1= repository.findByEmail(loginRequest.getEmail());
        if(student1.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new ApiResponse(false,"User not Found",null)
            );
        }
        Student student2 = student1.get();
        if(!passwordEncoder.matches(loginRequest.getPassword(),student2.getPassword())){
           return ResponseEntity.badRequest().body(
                   new ApiResponse(false,"Invalid password",null)
           );
        }
        return ResponseEntity.ok(
               new ApiResponse(true,"Login Successful",student2));

    }
}
