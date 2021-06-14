import {Component, Input, OnInit} from '@angular/core';
import {RestaurantModel} from "../../../models/restaurant.model";

@Component({
  selector: 'app-restaurant-data',
  templateUrl: './restaurant-data.component.html',
  styleUrls: ['./restaurant-data.component.css']
})
export class RestaurantDataComponent implements OnInit {

  @Input() restaurant: RestaurantModel;
  public stars: number[] = [1, 2, 3, 4, 5];
  public selectedValue: number = 0;

  constructor() { }

  ngOnInit(): void {
    this.restaurant.cuisine = this.restaurant.cuisine.filter((c) => c != 'NOT_IMPORTANT');
    this.selectedValue = this.getGrade();
  }

  countStar(star) {
    this.selectedValue = star;
  }

  getGrade(): number {
    let total: number = 0;
    this.restaurant.grades.forEach((g) => {
      total = total + (g.ambient + g.atmosphere + g.hospitability + g.location + g.overall + g.service);
    })

    total = total / (6 * this.restaurant.grades.length);

    return Math.ceil(total);
  }
}
