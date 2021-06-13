import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewUsersComponent } from './view-users/view-users.component';

const routes: Routes = [
  { path: 'view-users', component: ViewUsersComponent },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
