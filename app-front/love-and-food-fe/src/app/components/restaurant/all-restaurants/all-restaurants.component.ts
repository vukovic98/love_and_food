import { Component, OnInit } from '@angular/core';
import {RestaurantService} from "../../../services/restaurant.service";
import {RestaurantModel} from "../../../models/restaurant.model";
import {PageEvent} from "@angular/material/paginator";
import {FilterRestaurantsModel} from "../../../models/filter-restaurants.model";
import {MatchService} from "../../../services/match.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";

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

  private filterMode = false;
  private filterDto: FilterRestaurantsModel = null;
  public hasAMatch: boolean = false;

  constructor(
    private restaurantService: RestaurantService,
    private matchService: MatchService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.getData();
    this.matchService.userHasAMatch().subscribe((response) => {
      this.hasAMatch = true;
    })
  }

  getData() {
    if(!this.filterMode) {
      this.restaurantService.findAllByPage(this.pageIndex).subscribe((response) => {
        this.restaurants = response.content;
        this.length = response.totalElements;
      });
    } else {
      this.restaurantService.filterRestaurants(this.filterDto, this.pageIndex).subscribe((response) => {
        this.restaurants = response ? response.content : [];
        this.length = response? response.totalElements: 0;
      });
    }
  }

  handlePageEvent(event: PageEvent) {
    this.length = event.length;
    this.pageIndex = event.pageIndex;

    this.getData();
  }

  filter(data: FilterRestaurantsModel) {
    if(data.cuisine !== null || data.name !== null || data.location !== null || data.price !== null) {
      this.filterDto = data;
      this.filterMode = true;
      this.pageIndex = 0;
      this.getData();
    } else {
      this.pageIndex = 0;
      this.filterMode = false;
      this.getData();
    }
  }

  getRole(): string {
    return this.authService.getUserAuthorities()[0].authority;
  }

  findARestaurant() {
    this.router.navigate(['/restaurant/find-a-place'])
  }
}
