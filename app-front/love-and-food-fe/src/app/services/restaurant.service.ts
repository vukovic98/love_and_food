import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageObject} from "../models/page.model";
import {RestaurantModel} from "../models/restaurant.model";
import {environment} from "../../environments/environment";
import {AuthService} from "./auth.service";
import {FilterRestaurantsModel} from "../models/filter-restaurants.model";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private readonly RESTAURANT_API: string = "restaurant";
  private readonly RESTAURANT_PAGE_API: string = "restaurant/by-page/";
  private readonly RESTAURANT_FILTER_API: string = "restaurant/filter/by-page/";


  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  findAllByPage(page: number): Observable<PageObject<RestaurantModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.get<PageObject<RestaurantModel>>(environment.SERVER_APP + this.RESTAURANT_PAGE_API + page, { headers: headers });
  }

  createRestaurant(data: RestaurantModel): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.post(environment.SERVER_APP + this.RESTAURANT_API, data,{ headers: headers });
  }

  filterRestaurants(data: FilterRestaurantsModel, page: number): Observable<PageObject<RestaurantModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.post<PageObject<RestaurantModel>>(environment.SERVER_APP + this.RESTAURANT_FILTER_API + page, data, { headers: headers });
  }
}
