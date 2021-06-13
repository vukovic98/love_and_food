import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public role: string = '';

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.role = this.authService.getUserAuthorities()[0].authority;
  }

}
