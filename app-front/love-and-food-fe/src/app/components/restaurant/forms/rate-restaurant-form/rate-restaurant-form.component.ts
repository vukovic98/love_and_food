import {Component, Input, OnInit} from '@angular/core';
import {RestaurantModel} from '../../../../models/restaurant.model';
import {GradeModel} from '../../../../models/grade.model';
import {RestaurantService} from '../../../../services/restaurant.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-rate-restaurant-form',
  templateUrl: './rate-restaurant-form.component.html',
  styleUrls: ['./rate-restaurant-form.component.css']
})
export class RateRestaurantFormComponent implements OnInit {

  @Input() restaurant: RestaurantModel;

  public stars: number[] = [1, 2, 3, 4, 5];
  public ambient: number = 1;
  public service: number = 1;
  public hospitability: number = 1;
  public location: number = 1;
  public atmosphere: number = 1;
  public overall: number = 1;

  public hasGraded: boolean = false;
  public grade: GradeModel;

  constructor(
    private restaurantService: RestaurantService,
    private _snackBar: MatSnackBar
) { }

  ngOnInit(): void {
    this.restaurantService.hasGradedRestaurant(this.restaurant.restaurant_id).subscribe((response) => {
      this.grade = response;
      this.hasGraded = true;

      this.atmosphere = this.grade.atmosphere;
      this.ambient = this.grade.ambient;
      this.service = this.grade.service;
      this.overall = this.grade.overall;
      this.hospitability = this.grade.hospitability;
      this.location = this.grade.location;
    }, error => {
      this.hasGraded = false;
    })
  }

  countStar(star, type) {
    if(type === 'AMBIENT') this.ambient = star;
    if(type === 'SERVICE') this.service = star;
    if(type === 'HOSPITABILITY') this.hospitability = star;
    if(type === 'ATMOSPHERE') this.atmosphere = star;
    if(type === 'LOCATION') this.location = star;
    if(type === 'OVERALL') this.overall = star;
  }

  rateRestaurant() {
    const data: GradeModel = {
      ambient: this.ambient,
      atmosphere: this.atmosphere,
      hospitability: this.hospitability,
      location: this.location,
      overall: this.overall,
      restaurantID: this.restaurant.restaurant_id,
      service: this.service
    }

    this.restaurantService.gradeRestaurant(data).subscribe((response) => {
      this._snackBar.open("Restaurant successfully graded!", "Close", {
        duration: 2000,
        panelClass: ['orange-snackbar']
      });
    }, error => {
      this._snackBar.open("* Restaurant with this name already exists!", "Close", {
        duration: 2000,
        panelClass: ['orange-snackbar']
      })
    })
  }
}
