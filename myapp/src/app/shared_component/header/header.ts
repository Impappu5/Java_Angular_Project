import { Component } from '@angular/core';
import { AuthService } from '../../../servicess/auth-service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  constructor(private authService:AuthService, private router:Router,private toastr:ToastrService){}
  logout() {
  }
}

 



