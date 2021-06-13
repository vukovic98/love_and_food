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
import { FindRestaurantFormComponent } from './forms/find-restaurant-form/find-restaurant-form.component';
import {MatRadioModule} from "@angular/material/radio";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import { ChosenRestaurantComponent } from './choosen-restaurant/chosen-restaurant.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatCardModule} from "@angular/material/card";
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AddRestaurantFormComponent } from './forms/add-restaurant-form/add-restaurant-form.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { RateRestaurantComponent } from './rate-restaurant/rate-restaurant.component';
import { RateRestaurantItemComponent } from './rate-restaurant-item/rate-restaurant-item.component';

@NgModule({
  declarations: [
    AllRestaurantsComponent,
    RestaurantDataComponent,
    FilterRestaurantsComponent,
    FindRestaurantComponent,
    FindRestaurantFormComponent,
    ChosenRestaurantComponent,
    AddRestaurantComponent,
    AddRestaurantFormComponent,
    RateRestaurantComponent,
    RateRestaurantItemComponent
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
        MatSnackBarModule,
        MatDialogModule,
        MatProgressBarModule,
        MatCardModule,
        MatCheckboxModule,
    ]
})
export class RestaurantModule { }
