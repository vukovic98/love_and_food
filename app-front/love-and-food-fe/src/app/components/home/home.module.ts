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
import { UserPreferencesComponent } from './user-preferences/user-preferences.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    HomeComponent,
    UserPanelComponent,
    SoulmateComponent,
    RateMatchComponent,
    RateMatchItemComponent,
    RateMatchFormComponent,
    UserPreferencesComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    RouterModule,
    SharedModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule,
    MatProgressBarModule,
    MatCardModule,
    MatSnackBarModule,
    MatFormFieldModule,
  ]
})
export class HomeModule { }
