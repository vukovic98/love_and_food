import {Component, Input, OnInit} from '@angular/core';
import {RestaurantModel} from "../../../models/restaurant.model";
import {StarRatingComponent} from "ng-starrating";

@Component({
  selector: 'app-restaurant-data',
  templateUrl: './restaurant-data.component.html',
  styleUrls: ['./restaurant-data.component.css']
})
export class RestaurantDataComponent implements OnInit {

  @Input() restaurant: RestaurantModel;
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number = 0;

  constructor() { }

  ngOnInit(): void {
  }

  onRate($event:{oldValue:number, newValue:number, starRating:StarRatingComponent}) {
    alert(`Old Value:${$event.oldValue},
      New Value: ${$event.newValue},
      Checked Color: ${$event.starRating.checkedcolor},
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }

  countStar(star: any) {

    this.selectedValue = star;


  }

  getGrade() {
    return 5;
  }
}
