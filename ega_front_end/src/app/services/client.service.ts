import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../models/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  readonly API_URL = "http://localhost:8080"
  readonly ENDPOINT = "/api/clients"

  constructor(private httpClient: HttpClient ) { }


  getClients(): Observable<Client[]> {
    return this.httpClient.get<Client[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  saveClient(clientDto: any): Observable<Client> {
    return this.httpClient.post<Client>(`${this.API_URL}${this.ENDPOINT}`, clientDto);
  }

  getClientById(id: number): Observable<Client> {
    return this.httpClient.get<Client>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  deleteClient(id: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  updateClient(id: number, clientDto: any): Observable<Client> {
    return this.httpClient.put<Client>(`${this.API_URL}${this.ENDPOINT}/${id}`, clientDto);
  }



  



}