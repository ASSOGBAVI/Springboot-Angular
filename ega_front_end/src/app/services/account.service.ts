import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  readonly API_URL = "http://localhost:8080"
  readonly ENDPOINT = "/api/accounts"

  constructor(private httpClient: HttpClient ) { }
  getAccounts(): Observable<Account[]> {
    return this.httpClient.get<Account[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  saveAccount(accountDto: any): Observable<Account> {
    return this.httpClient.post<Account>(`${this.API_URL}${this.ENDPOINT}`, accountDto);
  }

  getAccountById(id: number): Observable<Account> {
    return this.httpClient.get<Account>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  deleteAccount(id: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}