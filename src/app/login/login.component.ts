import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscriber, Subscription } from 'rxjs';
import { CentralServiceService } from '../services/central-service.service';
// import { CentralServiceService } from '../services/central-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  formLogin: FormGroup = new FormGroup({});
  private sub = new Subscription;
  constructor(private central: CentralServiceService, private router: Router) { }
  
  ngOnInit(): void {
      this.formLogin = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required)
      })
  }

  login(){
    const cred: {
      username: string,
      password: string
    } = {
      username: this.formLogin.get('username')?.value,
      password: this.formLogin.get('password')?.value
    }
    this.central.getLogin(cred).subscribe({
      next:()=>console.log("accesso"),
      error:()=>console.log("credenziali sbagliate")
       
    });
  }
  ngOnDestroy(): void {
    if(this.sub){
      this.sub.unsubscribe();
    }
  }
}
