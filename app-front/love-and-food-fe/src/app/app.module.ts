import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './services/auth.service';
import {MatStepperModule} from '@angular/material/stepper';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent
  ],
    imports: [
      BrowserModule,
      HttpClientModule,
      AppRoutingModule,
      ReactiveFormsModule,
      MatStepperModule,
      MatButtonModule,
      BrowserAnimationsModule,
      MatIconModule,
      MatInputModule,
      MatSelectModule,
      MatRadioModule
    ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
