import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../servicess/auth-service';
import { CommonModule } from '@angular/common';
import { Toast, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-admin',
  imports: [CommonModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin {

 
  users:any;

  constructor(private authService: AuthService, private toastr:ToastrService) {}

ngOnInit(){
  this.authService.getAllUsers().subscribe({
    next: (res) =>{
      this.users=res;
      this.toastr.success("User loaded successful", "Success")
    },error: (err)=>{
      console.error(err);
      this.toastr.error("Failed to load users ", "Error")
    }

  })
  
  
}
 
}
