import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {ConfigurationRoutingModule} from "./configuration-routing.module";
import {SharedModule} from "../shared/shared.module";
import {MatButtonModule} from "@angular/material/button";
import { MatDialogModule } from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatCardModule } from '@angular/material/card';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {RestaurantPointsComponent} from "./restaurant-points/restaurant-points.component";
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  declarations: [
    RestaurantPointsComponent
  ],
  imports: [
    CommonModule,
    ConfigurationRoutingModule,
    RouterModule,
    SharedModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule,
    MatProgressBarModule,
    MatCardModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class ConfigurationModule { }
