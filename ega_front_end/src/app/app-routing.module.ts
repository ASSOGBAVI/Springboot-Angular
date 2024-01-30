import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AccountsComponent } from './accounts/accounts.component';
import { authGuard } from './guards/auth.guard';
import { TransactionsComponent } from './transactions/transactions.component';
import { NewTransactionComponent } from './new-transaction/new-transaction.component';
import { ClientsComponent } from './clients/clients.component';
import { NewClientComponent } from './new-client/new-client.component';
import { UpdateClientComponent } from './update-client/update-client.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  //{ path: 'accounts', component: AccountsComponent, canActivate: [authGuard] },
  {path:"accounts",component: AccountsComponent },
  //{ path: 'transactions', component: TransactionsComponent, canActivate: [authGuard] },
  { path: 'transactions', component: TransactionsComponent },
  { path: 'new-transaction', component: NewTransactionComponent },
  { path: 'clients', component: ClientsComponent },
  { path: 'new-client', component: NewClientComponent },
  { path: 'update_client', component: UpdateClientComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
