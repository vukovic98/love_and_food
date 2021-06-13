import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RestaurantService} from '../../../services/restaurant.service';
import {RestaurantModel} from '../../../models/restaurant.model';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

  public restaurant: RestaurantModel;
  public stars: number[] = [1, 2, 3, 4, 5];
  public selectedValue: number = 0;

  constructor(
    private route: ActivatedRoute,
    private restaurantService: RestaurantService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(parameter => {
      const id = parameter.id;
      this.restaurantService.findRestaurantById(id).subscribe((response) => {
        this.restaurant = response;
        this.restaurant.cuisine = this.restaurant.cuisine.filter((c) => c != 'NOT_IMPORTANT');
        this.selectedValue = this.getGrade();
      });
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
