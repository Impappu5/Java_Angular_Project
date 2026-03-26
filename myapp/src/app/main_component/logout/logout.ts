import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../servicess/auth-service';

@Component({
  selector: 'app-logout',
  imports: [],
  templateUrl: './logout.html',
  styleUrl: './logout.css',
})
export class Logout {

  constructor(private router:Router,private authService:AuthService){}
// logout() {
//   this.authService.logout(); // remove token
//   this.router.navigate(['/login']);
// }

}
