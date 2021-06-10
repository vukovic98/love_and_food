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
import { FilterRestaurantsComponent } from './filter-restaurants/filter-restaurants.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {ReactiveFormsModule} from "@angular/forms";
import { FindRestaurantComponent } from './find-restaurant/find-restaurant.component';
import { FindRestaurantFormComponent } from './find-restaurant-form/find-restaurant-form.component';
import {MatRadioModule} from "@angular/material/radio";

@NgModule({
  declarations: [
    AllRestaurantsComponent,
    RestaurantDataComponent,
    FilterRestaurantsComponent,
    FindRestaurantComponent,
    FindRestaurantFormComponent
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
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        ReactiveFormsModule,
        MatRadioModule,
    ]
})
export class RestaurantModule { }
