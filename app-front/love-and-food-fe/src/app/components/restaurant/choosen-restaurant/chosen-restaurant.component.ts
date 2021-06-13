import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {RestaurantEntryModel} from "../../../models/restaurant-entry.model";
import {RestaurantService} from "../../../services/restaurant.service";
import {RestaurantModel} from "../../../models/restaurant.model";

@Component({
  selector: 'app-chosen-restaurant',
  templateUrl: './chosen-restaurant.component.html',
  styleUrls: ['./chosen-restaurant.component.css']
})
export class ChosenRestaurantComponent implements OnInit {

  public found: boolean = false;
  public restaurant: RestaurantModel;
  public stars: number[] = [1, 2, 3, 4, 5];
  public selectedValue: number = 3;

  constructor(
    public dialogRef: MatDialogRef<ChosenRestaurantComponent>,
    @Inject(MAT_DIALOG_DATA) public data: RestaurantEntryModel,
    private restaurantService: RestaurantService
  ) { }

  ngOnInit(): void {
    this.restaurantService.findRestaurant(this.data).subscribe((response) => {
      this.restaurant = response;
      this.restaurant.cuisine = this.restaurant.cuisine.filter((c) => c != 'NOT_IMPORTANT');
      this.found = true;
      this.restaurant.cuisine = this.restaurant.cuisine.map((c) => c.toLowerCase());
    });
  }

  getGrade(): number {
    let total: number = 0;
    this.restaurant.grades.forEach((g) => {
      total = total + (g.ambient + g.atmosphere + g.hospitability + g.location + g.overall + g.service);
    })

    total = total / (6 * this.restaurant.grades.length);

    return Math.ceil(total);
  }

  hasSomethingElse(): string {
    let s: string = 'We also offer you these things: ';
    s += this.restaurant.alcohol ? 'various alcohol drinks, ' : '';
    s += this.restaurant.wifi ? 'fast internet, ': '';
    s += this.restaurant.tv ? 'TV with hundreds of canals, ': '';
    s += this.restaurant.parking ? 'parking space for your car, ' : '';
    s += this.restaurant.garden ? 'beautiful garden, ' : '';
    s += this.restaurant.liveMusic ? 'live music, ' : '';
    s += this.restaurant.smokingArea ? 'smoking area for those who smoke.' : '';

    if(s.endsWith(',')) {
      s.slice(0, s.length - 1);
      s += '.';
    }

    return  s;
  }

}
