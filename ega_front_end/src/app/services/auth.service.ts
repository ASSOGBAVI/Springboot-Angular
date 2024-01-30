import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //private baseUrl = 'http://localhost:8080'; // Remplacez par l'URL de votre backend


  // Simulated user data for demonstration purposes
  private users = [
    { id: 1, username: 'user1@gmail.com', password: 'password1' },
    { id: 2, username: 'user2@gmail.com', password: 'password2' }
    // Add more users as needed
  ];

  login(username: string, password: string): Observable<any> {
    // Simulate an asynchronous operation (e.g., API call)
    return new Observable(observer => {
      // Simulate a delay to mimic network latency
      setTimeout(() => {
        const user = this.users.find(u => u.username === username && u.password === password);

        if (user) {
          // Successful login
          observer.next({ success: true, user: { id: user.id, username: user.username } });
        } else {
          // Failed login
          observer.error({ success: false, message: 'Invalid credentials' });
        }

        // Complete the observable
        observer.complete();
      }, 1000); // Simulated delay of 1 second
    });
  }

}
