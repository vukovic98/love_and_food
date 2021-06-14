import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatchDTO } from 'src/app/dto/match.dto';
import { LoveService } from 'src/app/services/love.service';
import { MatchService } from 'src/app/services/match.service';

@Component({
  selector: 'app-rate-match-form',
  templateUrl: './rate-match-form.component.html',
  styleUrls: ['./rate-match-form.component.css']
})
export class RateMatchFormComponent implements OnInit {

  match: MatchDTO;

  public stars: number[] = [1, 2, 3, 4, 5]
  public rating: number = 1

  public hasGraded: boolean = false
  public grade: number

  constructor(
    private loveService: LoveService,
    private matchService: MatchService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loveService.matchInRating
      .subscribe( res => {
        //GET MATCH BY ID
        this.matchService.findById(res.id)
          .subscribe( match => {
            this.match = match
              if(match.rating != 0) {
                this.rating = match.rating
                this.hasGraded = true
              } else {
                this.rating = 1
                this.hasGraded = false
              }
          })
      })
  }

  countStar(star) {
    this.rating = star
  }

  rateTheDate() {
    this.loveService.rateDate(this.match.id, this.rating)
      .subscribe( res => {
        this._snackBar.open("Date successfully rated!", "Close", {
          duration: 2000,
          panelClass: ['purple-snackbar']
        });
        this.loveService.ratingInProgress.next(false)
      },
      error => {
        console.log(error)
      })
  }

}
