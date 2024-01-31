import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent {

  clientForm!: FormGroup;


  constructor(private fb: FormBuilder,
    private cs: ClientService,
    private router: Router) { }


  ngOnInit(): void {
    this.clientForm = this.fb.group({
      clientLastName: this.fb.control('', [Validators.required]),
      clientFirstName: this.fb.control('', [Validators.required]),
      birthDate: this.fb.control('', [Validators.required]),
      sexe: this.fb.control('', [Validators.required]),
      address: this.fb.control('', [Validators.required]),
      phone: this.fb.control('', [Validators.required]),
      nationality: this.fb.control('', [Validators.required])


    });
  }

  saveClient() {
    console.log('Saving client:', this.clientForm.value);

    let client = this.clientForm.value;
    this.cs.saveClient(client)
      .subscribe({
        next: data => {
          console.log('Save successful:', data);
          this.router.navigateByUrl("/clients");
        },
        error: err => {
          console.error('Save failed:', err);
        }
      });
  }


}
