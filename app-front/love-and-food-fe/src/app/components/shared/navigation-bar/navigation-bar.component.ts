import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth.service';
import {MatchService} from "../../../services/match.service";

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

  public role: string = '';
  public hasMatch: boolean = false;
  @Input() public mode: string = "LOVE";

  ngOnInit(): void {
    this.role = this.authService.getUserAuthorities()[0].authority;
    this.matchService.userHasAMatch().subscribe((response) => {
      this.hasMatch = true;
    }, error => {
      this.hasMatch = false;
    });
  }

  logout() {
    localStorage.removeItem('accessToken');
    this.route.navigate(['/']);
  }
}
