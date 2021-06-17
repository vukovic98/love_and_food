import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {ContactSoulmateComponent} from "../../contact/contact-soulmate/contact-soulmate.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public role: string = '';

  constructor(
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.role = this.authService.getUserAuthorities()[0].authority;

  }

}
