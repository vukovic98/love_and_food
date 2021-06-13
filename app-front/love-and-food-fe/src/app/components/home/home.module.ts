import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {HomeRoutingModule} from "./home-routing.module";
import {SharedModule} from "../shared/shared.module";
import {HomeComponent} from "./home/home.component";
import {MatButtonModule} from "@angular/material/button";
import { UserPanelComponent } from './user-panel/user-panel.component';
import { MatDialogModule } from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { SoulmateComponent } from './soulmate/soulmate.component';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [
    HomeComponent,
    UserPanelComponent,
    SoulmateComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    RouterModule,
    SharedModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule,
    MatProgressBarModule,
    MatCardModule,
  ]
})
export class HomeModule { }
