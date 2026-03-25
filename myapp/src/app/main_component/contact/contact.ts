import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ContactsService } from '../../../servicess/contacts-service';
import { AuthService } from '../../../servicess/auth-service';


@Component({
  selector: 'app-contact',
  imports: [FormsModule, CommonModule, ReactiveFormsModule, CommonModule,],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
})
export class Contact {

  contactForm = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(3)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(3)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    mobileNo: new FormControl('', [Validators.required, Validators.pattern("[0-9]{10}")]),
    feedback: new FormControl('', [Validators.required, Validators.minLength(3)]),

  })

  constructor(private authService:AuthService, private toastr: ToastrService) { }


  onSubmit() {
    this.authService.cantact(this.contactForm.value).subscribe({
      next: (res)=>{
        this.toastr.success("Form submit successful!","success")
        this.contactForm.reset();
      },error: (err)=>{
        this.toastr.error("Form not submit failed","error")
      }
    });
  } 


}

