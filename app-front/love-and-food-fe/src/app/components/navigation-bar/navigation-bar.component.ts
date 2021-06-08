import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {MatchService} from "../../services/match.service";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  constructor(
    private route: Router,
    private authService: AuthService,
    private matchService: MatchService
  ) { }

  role = '';

  ngOnInit(): void {
    this.role = this.authService.getUserAuthorities()[0].authority;
  }

  hasAMatch(): boolean {
    this.matchService.userHasAMatch().subscribe((response) => {
      return true;
    }, error => {
      return false;
    });

    return false;
  }

  logout() {
    localStorage.removeItem('accessToken');
    this.route.navigate(['/']);
  }
}
