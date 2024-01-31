import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../models/client';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private clients: Client[] = [/*
    {
      id: 1,
      clientLastName: 'Doe',
      clientFirstName: 'John',
      birthDate: '1990-01-01',
      sexe: 'Male',
      address: '123 Main St',
      phone: '1234567890',
      nationality: 'US',
      creationDate: '2022-01-01',
      accounts: [
        { id: 1, accountNumber: 'ABC123', balance: 1000 },
      ],
    },
    {
      id: 2,
      clientLastName: 'Smith',
      clientFirstName: 'Alice',
      birthDate: '1985-05-15',
      sexe: 'Female',
      address: '456 Oak Ave',
      phone: '9876543210',
      nationality: 'CA',
      creationDate: '2022-02-01',
      accounts: [
        { id: 2, accountNumber: 'XYZ789', balance: 2000 },

      ],
    },*/
  ];
  private isAuthenticated = false;

  private baseUrl = 'http://localhost:8080'; // Remplacez par l'URL de votre backend



  login(address: string, phone: string): Observable<any> {
    // Simulate an asynchronous operation (e.g., API call)
    return new Observable(observer => {
      // Simulate a delay to mimic network latency
      setTimeout(() => {
        const client = this.clients.find(u => u.address === address && u.phone === phone);
  
        if (client) {
          // Successful login
          observer.next({ success: true, client: { id: client.id, address: client.address } });
        } else {
          // Failed login
          observer.error({ success: false, message: 'Invalid credentials' });
        }
  
        // Complete the observable
        observer.complete();
      }, 1000); // Simulated delay of 1 second
    });
  }
  
  logout(): void {
    this.isAuthenticated = false;
  }

  isAuthenticatedUser(): boolean {
    return this.isAuthenticated;
  }



}
