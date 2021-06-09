import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {RestaurantRoutingModule} from "./restaurant-routing.module";
import {SharedModule} from "../shared/shared.module";
import {MatButtonModule} from "@angular/material/button";
import {AllRestaurantsComponent} from "./all-restaurants/all-restaurants.component";
import {MatPaginatorModule} from "@angular/material/paginator";
import { RestaurantDataComponent } from './restaurant-data/restaurant-data.component';
import {MatIconModule} from "@angular/material/icon";
import {RatingModule} from "ng-starrating";
import {NgbRatingModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  declarations: [
    AllRestaurantsComponent,
    RestaurantDataComponent
  ],
  imports: [
    CommonModule,
    RestaurantRoutingModule,
    RouterModule,
    SharedModule,
    MatButtonModule,
    MatPaginatorModule,
    MatIconModule,
    RatingModule,
    NgbRatingModule,
  ]
})
export class RestaurantModule { }
