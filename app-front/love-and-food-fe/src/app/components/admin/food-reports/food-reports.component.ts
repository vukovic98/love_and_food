import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RestaurantModel} from "../../../models/restaurant.model";
import {FoodReportsService} from "../../../services/food-reports.service";

@Component({
  selector: 'app-food-reports',
  templateUrl: './food-reports.component.html',
  styleUrls: ['./food-reports.component.css']
})
export class FoodReportsComponent implements OnInit {

  public seasonReport: boolean = false;
  public restaurants: Array<RestaurantModel> = [];

  reportForm = new FormGroup({
    report: new FormControl('', Validators.required),
    season: new FormControl({ value: null, disabled: false }),
  });

  constructor(
    private foodReportService: FoodReportsService
  ) { }

  ngOnInit(): void {
  }

  selectChanged(): void {
    this.seasonReport = this.reportForm.value.report === 'MOST_VISITED';
  }

  validSeason(): boolean {
    if (this.reportForm.value.report === 'MOST_VISITED') {
      return this.reportForm.value.season !== null;
    } else
      return true;
  }

  getFirst(): RestaurantModel {
    return this.restaurants[0];
  }

  getSecond(): RestaurantModel {
    return this.restaurants[1];
  }

  getThird(): RestaurantModel {
    return this.restaurants[2];
  }

  getReport(): void {
    if (this.reportForm.value.report === 'BEST_GRADED') {
      this.foodReportService.bestGradedReport().subscribe((response) => {
        this.restaurants = response;
        console.log(this.restaurants)
      }, error => {
        this.restaurants = [];
      });
    }

    if (this.reportForm.value.report === 'DECLINING') {
      this.foodReportService.decliningReport().subscribe((response) => {
        this.restaurants = response;
        console.log(this.restaurants)
      }, error => {
        this.restaurants = [];
      });
    }

    if (this.reportForm.value.report === 'RISING') {
      this.foodReportService.risingReport().subscribe((response) => {
        this.restaurants = response;
        console.log(this.restaurants)
      }, error => {
        this.restaurants = [];
      });
    }

    if (this.reportForm.value.report === 'MOST_VISITED') {
      this.foodReportService.mostVisitedReport(this.reportForm.value.season).subscribe((response) => {
        this.restaurants = response;
        console.log(this.restaurants)
      }, error => {
        this.restaurants = [];
      });
    }
  }
}
