import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RoleGuard} from "../../guards/role.guard";
import {AllRestaurantsComponent} from "./all-restaurants/all-restaurants.component";
import {FindRestaurantComponent} from "./find-restaurant/find-restaurant.component";
import {AddRestaurantComponent} from "./add-restaurant/add-restaurant.component";
import {RateRestaurantComponent} from "./rate-restaurant/rate-restaurant.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: AllRestaurantsComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_USER|ROLE_ADMIN'}
  },
  {
    path: 'find-a-place',
    component: FindRestaurantComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_USER'}
  },
  {
    path: 'add-restaurant',
    component: AddRestaurantComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'rate-restaurant',
    component: RateRestaurantComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_USER'}
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantRoutingModule { }
