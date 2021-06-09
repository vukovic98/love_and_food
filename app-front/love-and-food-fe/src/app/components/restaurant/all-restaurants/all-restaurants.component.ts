import { Component, OnInit } from '@angular/core';
import {RestaurantService} from "../../../services/restaurant.service";
import {RestaurantModel} from "../../../models/restaurant.model";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-all-restaurants',
  templateUrl: './all-restaurants.component.html',
  styleUrls: ['./all-restaurants.component.css']
})
export class AllRestaurantsComponent implements OnInit {

  public restaurants: Array<RestaurantModel> = [];

  public length = 0;
  public pageSize = 6;
  public pageIndex = 0;
  public showFirstLastButtons = true;

  constructor(
    private restaurantService: RestaurantService
  ) { }

  ngOnInit(): void {
    this.restaurantService.findAllByPage(this.pageIndex).subscribe((response) => {
      this.restaurants = response.content;
      this.length = response.totalElements;
      console.log(this.restaurants)
    })
  }

  handlePageEvent(event: PageEvent) {
    this.length = event.length;
    this.pageIndex = event.pageIndex;

    this.restaurantService.findAllByPage(this.pageIndex).subscribe((response) => {
      this.restaurants = response.content;
      this.length = response.totalElements;
    })
  }
}
