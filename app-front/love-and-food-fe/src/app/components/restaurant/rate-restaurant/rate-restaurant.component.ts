import { Component, OnInit } from '@angular/core';
import {DatePlaceService} from "../../../services/date-place.service";
import {AuthService} from "../../../services/auth.service";
import {DatePlaceModel} from "../../../models/date-place.model";

@Component({
  selector: 'app-rate-restaurant',
  templateUrl: './rate-restaurant.component.html',
  styleUrls: ['./rate-restaurant.component.css']
})
export class RateRestaurantComponent implements OnInit {

  private userId: number = -1;
  public datePlaces: Array<DatePlaceModel> = [];

  constructor(
    private datePlaceService: DatePlaceService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.userId = this.authService.getUserID();
    this.datePlaceService.getAllForUser(this.userId).subscribe((response) => {
      this.datePlaces = response;
    })
  }

}
