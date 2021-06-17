import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from "../shared/shared.module";
import { AdminRoutingModule } from './admin-routing.module';
import { ViewUsersComponent } from './view-users/view-users.component';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ViewAllMatchesComponent } from './view-all-matches/view-all-matches.component';
import { MatSortModule } from '@angular/material/sort';
import { FoodReportsComponent } from './food-reports/food-reports.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import { UserReportsComponent } from './user-reports/user-reports.component';
import { AlarmsComponent } from './alarms/alarms.component';

@NgModule({
  declarations: [
    ViewUsersComponent,
    ViewAllMatchesComponent,
    FoodReportsComponent,
    UserReportsComponent,
    AlarmsComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SharedModule,
    MatTableModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
  ]
})
export class AdminModule { }
