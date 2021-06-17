import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {NavigationBarComponent} from "./navigation-bar/navigation-bar.component";
import {MatStepperModule} from "@angular/material/stepper";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import { NotFoundComponent } from './not-found/not-found.component';



@NgModule({
  declarations: [
    NavigationBarComponent,
    NotFoundComponent,
  ],
    imports: [
        CommonModule,
        RouterModule,
        MatStepperModule,
        MatButtonModule,
        MatIconModule,
        MatInputModule,
        MatSelectModule,
        MatRadioModule,
    ],
    exports: [
        NavigationBarComponent,
    ]
})
export class SharedModule { }
