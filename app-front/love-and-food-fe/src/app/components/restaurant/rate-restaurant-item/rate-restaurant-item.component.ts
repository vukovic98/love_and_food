import {Component, Input, OnInit} from '@angular/core';
import {DatePlaceModel} from "../../../models/date-place.model";

@Component({
  selector: 'app-rate-restaurant-item',
  templateUrl: './rate-restaurant-item.component.html',
  styleUrls: ['./rate-restaurant-item.component.css']
})
export class RateRestaurantItemComponent implements OnInit {

  @Input() datePlace: DatePlaceModel;

  constructor() { }

  ngOnInit(): void {
  }

}
