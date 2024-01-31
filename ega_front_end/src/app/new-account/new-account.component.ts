import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountService } from '../services/account.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.css']
})
export class NewAccountComponent {

  accountForm!: FormGroup;


  constructor(private fb: FormBuilder,
    private cs: AccountService,
    private router: Router) { }

  ngOnInit(): void {
    this.accountForm = this.fb.group({
      accountType: this.fb.control('', [Validators.required]),
      ownerName: this.fb.control('', [Validators.required])

    });
  }

  saveAccount() {
    console.log('Saving account', this.accountForm.value);

    let account = this.accountForm.value;
    this.cs.saveAccount(account)
      .subscribe({
        next: data => {
          this.router.navigateByUrl("/accounts");
        },
        error: err => {
          console.log('Save failed:',err);
        }
      });


  }



}


