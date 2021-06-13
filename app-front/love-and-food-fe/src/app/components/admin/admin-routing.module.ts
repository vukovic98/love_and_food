import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewAllMatchesComponent } from './view-all-matches/view-all-matches.component';
import { ViewUsersComponent } from './view-users/view-users.component';

const routes: Routes = [
  { path: 'view-users', component: ViewUsersComponent },
  { path: 'view-all-matches', component: ViewAllMatchesComponent },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
