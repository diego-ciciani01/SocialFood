import { Component, OnInit, OnDestroy} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscriber, Subscription } from 'rxjs';
import { CentralServiceService } from '../services/central-service.service';
@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})
export class RegistrazioneComponent implements OnInit,OnDestroy {


  formRegistrati: FormGroup = new FormGroup({});
  private sub = new Subscription;
  constructor(private central: CentralServiceService, private router: Router) { }


  ngOnInit(): void {
    this.formRegistrati = new FormGroup({
      nome: new FormControl('', Validators.required),
      cognome: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    })
  }

  registrazione() {
    const cred: {
      nome: string,
      cognome: string,
      email: string,
      username: string,
      password: string
    } = {
      nome: this.formRegistrati.get('nome')?.value,
      cognome: this.formRegistrati.get('cognome')?.value,
      email: this.formRegistrati.get('email')?.value,
      username: this.formRegistrati.get('username')?.value,
      password: this.formRegistrati.get('password')?.value

    }
  }
  ngOnDestroy(): void {
    if(this.sub){
      this.sub.unsubscribe();
    }
  }
}
