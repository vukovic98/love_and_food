import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {RoleGuard} from "../../guards/role.guard";
import { RateMatchComponent } from './rate-match/rate-match.component';
import { UserPreferencesComponent } from './user-preferences/user-preferences.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomeComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_USER|ROLE_ADMIN'}
  },
  { path: 'rate-date', component: RateMatchComponent },
  { path: 'partner-preferences', component: UserPreferencesComponent },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
