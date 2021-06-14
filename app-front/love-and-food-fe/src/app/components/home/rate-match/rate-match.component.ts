import { Component, OnInit } from '@angular/core';
import { MatchDTO } from 'src/app/dto/match.dto';
import { LoveService } from 'src/app/services/love.service';
import { MatchService } from 'src/app/services/match.service';

@Component({
  selector: 'app-rate-match',
  templateUrl: './rate-match.component.html',
  styleUrls: ['./rate-match.component.css']
})
export class RateMatchComponent implements OnInit {

  public matches: Array<MatchDTO> = [];
  public ratingInProgress: boolean = false;

  constructor(
    private matchService: MatchService,
    private loveService: LoveService
  ) { }

  ngOnInit(): void {
    this.matchService.findAllMatchesForUser()
      .subscribe(
        res => {
          this.matches = res
        }
      )
    this.loveService.ratingInProgress
        .subscribe( res => {
          this.ratingInProgress = res
      })
  }
}
