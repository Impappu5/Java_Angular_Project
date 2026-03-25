import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../servicess/auth-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin',
  imports: [CommonModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin {

  users: any[] = []; // must be an array

  constructor(private authService: AuthService) {}

 
}
