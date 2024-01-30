import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Transaction } from '../models/transaction';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent {
  transactions: Array<Transaction> = [];



  constructor(private router:Router){}


}
