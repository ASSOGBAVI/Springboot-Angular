import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent {

  clientId!: number;
  clientForm!: FormGroup;

  constructor(private activatedRoute: ActivatedRoute,
    private cs: ClientService,
    private fb: FormBuilder,
    private router:Router
  ) { }


  ngOnInit() {
    this.clientId = this.activatedRoute.snapshot.params['id'];
    this.cs.getClientById(this.clientId)
      .subscribe({
        next: client => {
          this.clientForm = this.fb.group({
            id: this.fb.control(client.id),
            clientLastName: this.fb.control(client.clientLastName,
              [Validators.required])
          })
        },
        error: err => {
          console.log(err);
        }
      });

  }


  updateClient() {
    let updatedClient = this.clientForm.value;
    let clientId = updatedClient.id; // Assuming the client object has an 'id' property
  
    this.cs.updateClient(clientId, updatedClient)
      .subscribe({
        next: data => {
          //alert(JSON.stringify(data));
          this.router.navigateByUrl("/clients");
        },
        error: err => {
          console.log(err);
        }
      });
  }
  
  
}
