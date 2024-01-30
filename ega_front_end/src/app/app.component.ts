import { Component } from '@angular/core';

import { AccountService } from './services/account.service';

import { OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AccountService]
})
export class AppComponent {
  title = 'ega_front_end';


  //constructor(private accountService : AccountService) {}


}
