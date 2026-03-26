import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../servicess/auth-service';




@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  })
  // auth: any;


  constructor(private toastr: ToastrService, private router: Router, private authService: AuthService) {

  }

  onLogin() {
      this.authService.login(this.form.value).subscribe({
        next:(res) =>{
          
          // localStorage.setItem('token', res.token);
  
          this.authService.setToken(res.token);
 
          this.toastr.success("User Login Successfil", "Success");
           this.router.navigate(['/dashboard']);
          this.form.reset();
        },
      error:(err)=> {
        this.toastr.error("Login Failed","Error");
      }
    });
   }

  }

