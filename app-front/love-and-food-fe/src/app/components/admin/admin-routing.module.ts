import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserReportsComponent } from './user-reports/user-reports.component';
import { ViewAllMatchesComponent } from './view-all-matches/view-all-matches.component';
import { ViewUsersComponent } from './view-users/view-users.component';
import {RoleGuard} from "../../guards/role.guard";
import {AlarmsComponent} from "./alarms/alarms.component";

const routes: Routes = [
  {
    path: 'view-users',
    component: ViewUsersComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'view-all-matches',
    component: ViewAllMatchesComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'user-reports',
    component: UserReportsComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'user-reports',
    component: UserReportsComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'alarms',
    component: AlarmsComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
