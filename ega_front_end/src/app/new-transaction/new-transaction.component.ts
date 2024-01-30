import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientService } from '../services/client.service';
import { Router } from '@angular/router';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-new-transaction',
  templateUrl: './new-transaction.component.html',
  styleUrls: ['./new-transaction.component.css']
})
export class NewTransactionComponent {
  selectedForm: string = 'form1';
  transactionForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private cs: TransactionService,
    private router: Router) { }

  toggleForm(form: string) {
    this.selectedForm = form;
  }


  ngOnInit(): void {
    this.transactionForm = this.fb.group({
      sourceAccountId: this.fb.control('', [Validators.required]),
      recipientAccountId: this.fb.control('', [Validators.required]),
      amount: this.fb.control('', [Validators.required])
    });
  }

  makeDeposit(){}

  makeWithdrawal(){}

  makeTransfer(){}



}
