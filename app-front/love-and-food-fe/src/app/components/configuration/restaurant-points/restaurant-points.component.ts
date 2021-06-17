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
  public lastConfig: ConfigureRestaurantModel = null;
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
    ambientPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.ambientPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    smokingPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.smokingPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    alcoholPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.alcoholPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    dateTimePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.dateTimePoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    distancePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.distancePoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    priceRangePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.priceRangePoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    musicPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.musicPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    cuisinePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.cuisinePoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    gardenPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.gardenPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    parkingPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.parkingPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    tvPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.tvPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),
    wifiPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.wifiPoints : 0, disabled: false }, [Validators.required, Validators.min(0)]),

    baseAmbientPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseAmbientPoints : 0, disabled: false }, [Validators.required]),
    baseSmokingPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseSmokingPoints : 0, disabled: false }, [Validators.required]),
    baseAlcoholPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseAlcoholPoints : 0, disabled: false }, [Validators.required]),
    baseDateTimePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseDateTimePoints : 0, disabled: false }, [Validators.required]),
    baseDistancePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseDistancePoints : 0, disabled: false }, [Validators.required]),
    basePriceRangePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.basePriceRangePoints : 0, disabled: false }, [Validators.required]),
    baseMusicPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseMusicPoints : 0, disabled: false }, [Validators.required]),
    baseCuisinePoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseCuisinePoints : 0, disabled: false }, [Validators.required]),
    baseGardenPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseGardenPoints : 0, disabled: false }, [Validators.required]),
    baseParkingPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseParkingPoints : 0, disabled: false }, [Validators.required]),
    baseTvPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseTvPoints : 0, disabled: false }, [Validators.required]),
    baseWifiPoints: new FormControl({ value: this.lastConfig ? this.lastConfig.baseWifiPoints : 0, disabled: false }, [Validators.required])
  });

  constructor(
    private restaurantService: RestaurantService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.restaurantService.getLastConfig().subscribe((response) => {
      this.lastConfig = response;
      this.configForm.reset(this.lastConfig);
    })
  }

  submitConfiguration() {
    this.working = true;
    let data: ConfigureRestaurantModel = this.configForm.value;

    this.restaurantService.configureRestaurant(data).subscribe((response) => {
      this.working = false;
      this._snackBar.open("Restaurant configuration successfully changed!", "Close", {
        duration: 2000,
        panelClass: ['orange-snackbar']
      });
    }, error => {
      this.working = false;
      this._snackBar.open("* Something went wrong!", "Close", {
        duration: 2000,
        panelClass: ['orange-snackbar']
      })
    });
  }
}
