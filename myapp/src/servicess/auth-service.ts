import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { of } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {

  }

  private apiUrl = 'http://localhost:8081/student';
  


  signup(data: any) {
   return this.http.post(`${this.apiUrl}/signup`, data);

  }

  login(data: any): Observable<any> {
     return this.http.post(`${this.apiUrl}/login`, data);
  }

  cantact(data:any){
    return this.http.post(`${this.apiUrl}/contact`,data);
  }

  // ✅ Token handling
  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  clearToken() {
    localStorage.removeItem('token');
  }
}



