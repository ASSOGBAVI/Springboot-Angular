import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../models/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  readonly API_URL = "http://localhost:8080"
  readonly ENDPOINT = "/api/accounts"

  constructor(private httpClient: HttpClient ) { }

 
  getTransactions(): Observable<Transaction[]> {
    return this.httpClient.get<Transaction[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  saveTransaction(transactionDto: any): Observable<Transaction> {
    return this.httpClient.post<Transaction>(`${this.API_URL}${this.ENDPOINT}`, transactionDto);
  }

  getTransactionById(id: number): Observable<Transaction> {
    return this.httpClient.get<Transaction>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  deleteTransaction(id: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }



  makeDeposit(accountId: string, amount: number): Observable<Transaction> {
    return this.httpClient.post<Transaction>(`${this.API_URL}${this.ENDPOINT}/makeDeposit/${accountId}/${amount}`, {});
  }

  makeWithdrawal(accountId: string, amount: number): Observable<Transaction> {
    return this.httpClient.post<Transaction>(`${this.API_URL}${this.ENDPOINT}/makeWithdrawal/${accountId}/${amount}`, {});
  }

  makeTransfer(sourceAccountId: string, recipientAccountId: string, amount: number): Observable<Transaction> {
    return this.httpClient.post<Transaction>(`${this.API_URL}${this.ENDPOINT}/makeTransfer/${sourceAccountId}/${recipientAccountId}/${amount}`, {});
  }



}