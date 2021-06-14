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
import { RateMatchComponent } from './rate-match/rate-match.component';
import { RateMatchItemComponent } from './rate-match-item/rate-match-item.component';
import { RateMatchFormComponent } from './rate-match-form/rate-match-form.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    HomeComponent,
    UserPanelComponent,
    SoulmateComponent,
    RateMatchComponent,
    RateMatchItemComponent,
    RateMatchFormComponent
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
    MatSnackBarModule
  ]
})
export class HomeModule { }
