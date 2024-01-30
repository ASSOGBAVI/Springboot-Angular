import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from '../services/account.service';
import { Account } from '../models/account';
providers: [AccountService];


@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent {
  accounts: Array<Account> = [];


  constructor(private fb:FormBuilder,
     private router:Router,
     private accountService : AccountService){}

  //tableAccounts: Array<any> = [];

  ngOnInit(): void {
    this.loadAccounts();
  }

  loadAccounts() {
    this.accountService.getAccounts().subscribe(data => {
      this.accounts = data;
    });
  }

  saveAccount(accountDto: any) {
    this.accountService.saveAccount(accountDto).subscribe(response => {
      // Handle the response as needed
      console.log('Account saved successfully:', response);
      // Reload the accounts after saving
      this.loadAccounts();
    });
  }

  getAccountById(id: number) {
    this.accountService.getAccountById(id).subscribe(data => {
      // Handle the response as needed
      console.log('Account details:', data);
    });
  }

  deleteAccount(id: number) {
    this.accountService.deleteAccount(id).subscribe(response => {
      // Handle the response as needed
      console.log('Account deleted successfully:', response);
      // Reload the accounts after deletion
      this.loadAccounts();
    });
  }

}
