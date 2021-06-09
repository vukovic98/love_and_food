import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {HomeRoutingModule} from "./home-routing.module";
import {SharedModule} from "../shared/shared.module";
import {HomeComponent} from "./home/home.component";
import {MatButtonModule} from "@angular/material/button";
import { UserPanelComponent } from './user-panel/user-panel.component';

@NgModule({
  declarations: [
    HomeComponent,
    UserPanelComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    RouterModule,
    SharedModule,
    MatButtonModule,
  ]
})
export class HomeModule { }
