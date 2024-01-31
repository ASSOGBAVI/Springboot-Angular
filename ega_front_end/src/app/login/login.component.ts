import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  address: string = '';
  phone: string = '';
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login(): void {
    // Call the authentication service with the entered values
    this.authService.login(this.address, this.phone).subscribe(
      (response) => {
        // Handle successful login
        console.log('Login successful:', response);
        // Redirect to a specific route if needed
        this.router.navigate(['/accounts']);
      },
      (error) => {
        // Handle login error
        console.error('Login failed:', error);
        alert('Login failed. Please check your credentials and try again.');
      }
    );
  }
/*
  login() {
    // Validate the form data
    if (!this.isValidEmail(this.username)) {
      alert('Please enter a valid email address.');
      return;
    }

    if (this.password === '') {
      alert('Password cannot be empty.');
      return;
    }

    // Call the authentication service
    this.authService.login(this.username, this.password)
      .subscribe(Response => {
        // Handle successful login
        console.log('Login successful:', Response);
        // Redirect to a specific route if needed
        this.router.navigate(['/accounts']);
      }, error => {
        // Handle login error
        console.error('Login failed:', error);
        alert('Login failed. Please check your credentials and try again.');
      });
  }

  // Function to validate email format
  private isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
  */
}
