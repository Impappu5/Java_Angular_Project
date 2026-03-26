import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../../servicess/auth-service';

@Component({
  selector: 'app-user-list',
  imports: [],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css',
})
export class UserList {

  user:any

  constructor(private http:HttpClient, private toast:ToastrService,private auth:AuthService){}

  ngOnInit(){
    this.auth.getMyProfile().subscribe({
      next: (res) =>{
        this.user=res;
        this.toast.success("MyProfile loaded","Success");
      },error: (err)=>{
        console.error(err);
        this.toast.error("Failed profile","Error");
      }
    })
  }

}
