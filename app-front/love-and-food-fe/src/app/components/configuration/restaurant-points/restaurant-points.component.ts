import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ConfigureRestaurantModel} from "../../../models/configure.restaurant.model";
import {RestaurantService} from "../../../services/restaurant.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-restaurant-points',
  templateUrl: './restaurant-points.component.html',
  styleUrls: ['./restaurant-points.component.css']
})
export class RestaurantPointsComponent implements OnInit {

  public working: boolean = false;
  public data: Array<[string, string]> = [
    ["ambientPoints", "baseAmbientPoints"],
  ["smokingPoints", "baseSmokingPoints"],
  ["alcoholPoints", "baseAlcoholPoints"],
  ["dateTimePoints", "baseDateTimePoints"],
  ["distancePoints", "baseDistancePoints"],
  ["priceRangePoints", "basePriceRangePoints"],
  ["musicPoints", "baseMusicPoints"],
  ["cuisinePoints", "baseCuisinePoints"],
  ["gardenPoints", "baseGardenPoints"],
  ["parkingPoints", "baseParkingPoints"],
  ["tvPoints", "baseTvPoints"],
  ["wifiPoints", "baseWifiPoints"]];

  configForm = new FormGroup({
    ambientPoints: new FormControl('', [Validators.required, Validators.min(0)]),
    smokingPoints: new FormControl('', [Validators.required, Validators.min(0)]),
    alcoholPoints: new FormControl('', [Validators.required, Validators.min(0)]),
    dateTimePoints: new FormControl('', [Validators.required, Validators.min(0)]),
    distancePoints: new FormControl('', [Validators.required, Validators.min(0)]),
    priceRangePoints: new FormControl('', [Validators.required, Validators.min(0)]),
    musicPoints: new FormControl('', [Validators.required, Validators.min(0)]),
    cuisinePoints: new FormControl('', [Validators.required, Validators.min(0)]),
    gardenPoints: new FormControl('', [Validators.required, Validators.min(0)]),
    parkingPoints: new FormControl('', [Validators.required, Validators.min(0)]),
    tvPoints: new FormControl('', [Validators.required, Validators.min(0)]),
    wifiPoints: new FormControl('', [Validators.required, Validators.min(0)]),

    baseAmbientPoints: new FormControl('', [Validators.required]),
    baseSmokingPoints: new FormControl('', [Validators.required]),
    baseAlcoholPoints: new FormControl('', [Validators.required]),
    baseDateTimePoints: new FormControl('', [Validators.required]),
    baseDistancePoints: new FormControl('', [Validators.required]),
    basePriceRangePoints: new FormControl('', [Validators.required]),
    baseMusicPoints: new FormControl('', [Validators.required]),
    baseCuisinePoints: new FormControl('', [Validators.required]),
    baseGardenPoints: new FormControl('', [Validators.required]),
    baseParkingPoints: new FormControl('', [Validators.required]),
    baseTvPoints: new FormControl('', [Validators.required]),
    baseWifiPoints: new FormControl('', [Validators.required])
  });

  constructor(
    private restaurantService: RestaurantService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  submitConfiguration() {
    this.working = true;
    let data: ConfigureRestaurantModel = this.configForm.value;

    this.restaurantService.configureRestaurant(data).subscribe((response) => {
      this.working = false;
      this.configForm.reset();
      this._snackBar.open("Restaurant configuration successfully changed!", "Close", {
        duration: 2000,
        panelClass: ['orange-snackbar']
      });
    }, error => {
      this._snackBar.open("* Something went wrong!", "Close", {
        duration: 2000,
        panelClass: ['orange-snackbar']
      })
    });
  }
}
