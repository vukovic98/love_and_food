import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatchDTO } from 'src/app/dto/match.dto';
import { LoveService } from 'src/app/services/love.service';

@Component({
  selector: 'app-rate-match-item',
  templateUrl: './rate-match-item.component.html',
  styleUrls: ['./rate-match-item.component.css']
})
export class RateMatchItemComponent implements OnInit {

  @Input() match: MatchDTO;

  constructor(private loveService: LoveService) { }

  ngOnInit(): void {
  }

  rateTheDate(match: MatchDTO) {
    this.loveService.matchInRating.next(match)
    this.loveService.ratingInProgress.next(true)
  }
}
