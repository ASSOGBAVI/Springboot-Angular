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


  ngOnInit(): void {
    this.loadAccounts();
  }

  loadAccounts() {
    this.accountService.getAccounts().subscribe(data => {
      this.accounts = data;
    });
  }



  getAccountById(id: number) {
    this.accountService.getAccountById(id).subscribe(data => {
      // Handle the response as needed
      console.log('Account details:', data);
    });
  }

  deleteAccount(id: number) {
    if (confirm('Are you sure to delete this ?')) {
      this.accountService.deleteAccount(id).subscribe({
        next: value => {
          this.accounts = this.accounts.filter(account=> account.id !== id);
        }
      });
    }
  }

  updateClient(account: Account) {
    this.router.navigateByUrl(`update-account/${account.id}`);
  }



}
