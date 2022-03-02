import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscriber, Subscription } from 'rxjs';
import { CentralServiceService } from '../services/central-service.service';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})

export class HomepageComponent implements OnInit, OnDestroy{

  private sub = new Subscription;
  constructor(private central: CentralServiceService, private router: Router) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  ngOnDestroy(): void {
    if(this.sub){
      this.sub.unsubscribe();
    }
  }
  
}
