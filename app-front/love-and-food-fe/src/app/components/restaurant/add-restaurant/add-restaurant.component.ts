import { Component, OnInit } from '@angular/core';
import {RestaurantModel} from "../../../models/restaurant.model";
import {RestaurantService} from "../../../services/restaurant.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent implements OnInit {

  constructor(
    private restaurantService: RestaurantService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  createRestaurant(data: RestaurantModel) {
    if(data != null) {
      this.restaurantService.createRestaurant(data).subscribe((response) => {
        this._snackBar.open("Restaurant successfully added!", "Close", {
          duration: 2000,
          panelClass: ['orange-snackbar']
        });
      }, error => {
        this._snackBar.open("* Something went wrong!", "Close", {
          duration: 2000,
          panelClass: ['orange-snackbar']
        })
      })
    }
  }
}
