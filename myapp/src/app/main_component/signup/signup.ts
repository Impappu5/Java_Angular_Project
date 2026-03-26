import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { AuthService } from '../../../servicess/auth-service';





@Component({
  selector: 'app-signup',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css',
})
export class Signup {
  

  registerForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(3)]),
    confirmPassword: new FormControl('', [Validators.required])

  })
 

  constructor(private toastr: ToastrService, private authService: AuthService) {

  }

  submit() {
  this.authService.signup(this.registerForm.value).subscribe({
    next: (res) => {
      this.toastr.success("Signup Successful", "Success");
      this.registerForm.reset();
    },
    error: (err) => {
      console.log(err); // 🔍 check real error
      this.toastr.error("Signup Failed", "Error");
    }
  });

  }
}