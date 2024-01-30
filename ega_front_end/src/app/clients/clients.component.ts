import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from '../models/client';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent {
  clients: Array<Client> = [];

  constructor(private fb: FormBuilder,
    private router: Router,
    private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients() {
    this.clientService.getClients()
    .subscribe({
      next: data => {
        this.clients = data
      },
      error: err => {
        console.log(err);
      }
    })
  }


  deleteClient(id: number) {
    if (confirm('Are you sure to delete this ?')) {
      this.clientService.deleteClient(id).subscribe({
        next: value => {
          this.clients = this.clients.filter(client => client.id !== id);
        }
      });
    }
  }

  updateClient(client: Client) {
    this.router.navigateByUrl(`update-client/${client.id}`);
  }


}

