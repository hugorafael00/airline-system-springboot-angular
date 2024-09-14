import { Component, inject, ViewChild, Output, EventEmitter } from '@angular/core';

import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '/workspaces/108098262/angg/src/app/auth/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ ReactiveFormsModule, RouterModule ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  @Output() userError  =  new  EventEmitter<string>();

  authService  =  inject(AuthService);
  router  =  inject(Router);
  @ViewChild('message') message: any;

  onUserError() {
    this.message.nativeElement.style.display = 'block';
  }

  public registerForm  =  new  FormGroup({
    name:  new  FormControl('',  [Validators.required]),
    email:  new  FormControl('',  [Validators.required,  Validators.email]),
    password:  new  FormControl('',  [Validators.required])
  })

  public onSubmit(){
    if(this.registerForm.valid){
      console.log(this.registerForm.value);
      this.authService.signUp(this.registerForm.value)
      .subscribe(
        response => {
          if(response.status == 200){
            console.log(response.status);
            console.log(response.body);
            this.router.navigate(['/login']);
          }
          console.log("error");
        }, error => {
            console.log("error.status");
            if(error.status == 403){
              this.onUserError();
            }
            else{
              console.log(error);
            }

        });
    }
  }
}
