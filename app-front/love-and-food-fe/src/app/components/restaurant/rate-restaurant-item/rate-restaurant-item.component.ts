import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DatePlaceModel} from "../../../models/date-place.model";
import {RestaurantModel} from '../../../models/restaurant.model';

@Component({
  selector: 'app-rate-restaurant-item',
  templateUrl: './rate-restaurant-item.component.html',
  styleUrls: ['./rate-restaurant-item.component.css']
})
export class RateRestaurantItemComponent implements OnInit {

  @Input() datePlace: DatePlaceModel;
  @Output() rateRestaurantEvent = new EventEmitter<RestaurantModel>();

  constructor() { }

  ngOnInit(): void {
  }

  rateRestaurant(restaurant: RestaurantModel) {
    this.rateRestaurantEvent.emit(restaurant);
  }
}
