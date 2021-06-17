import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RoleGuard} from "../../guards/role.guard";
import {RestaurantPointsComponent} from "./restaurant-points/restaurant-points.component";

const routes: Routes = [
  {
    path: 'restaurant-points',
    pathMatch: 'full',
    component: RestaurantPointsComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfigurationRoutingModule { }
