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

@NgModule({
  declarations: [
    ViewUsersComponent,
    ViewAllMatchesComponent
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
    MatSortModule
  ]
})
export class AdminModule { }
