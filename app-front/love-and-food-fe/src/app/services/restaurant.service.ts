import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageObject} from "../models/page.model";
import {RestaurantModel} from "../models/restaurant.model";
import {environment} from "../../environments/environment";
import {AuthService} from "./auth.service";
import {FilterRestaurantsModel} from "../models/filter-restaurants.model";
import {RestaurantEntryModel} from "../models/restaurant-entry.model";
import {GradeModel} from '../models/grade.model';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private readonly RESTAURANT_API: string = "restaurant";
  private readonly RESTAURANT_PAGE_API: string = "restaurant/by-page/";
  private readonly FIND_RESTAURANT_API: string = "restaurant/find-restaurant";
  private readonly RESTAURANT_FILTER_API: string = "restaurant/filter/by-page/";
  private readonly GRADE_RESTAURANT_API: string = "restaurant/grade-restaurant";
  private readonly HAS_GRADED_API: string = "restaurant/has-graded/";

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(
    private http: HttpClient,
  ) { }

  findRestaurant(data: RestaurantEntryModel): Observable<RestaurantModel> {
    return this.http.post<RestaurantModel>(environment.SERVER_APP + this.FIND_RESTAURANT_API, data, { headers: this.headers });
  }

  findRestaurantById(id: number): Observable<RestaurantModel> {
    return this.http.get<RestaurantModel>(environment.SERVER_APP + this.RESTAURANT_API + "/" + id, { headers: this.headers });
  }

  hasGradedRestaurant(restaurantID: number): Observable<GradeModel> {
    return this.http.get<GradeModel>(environment.SERVER_APP + this.HAS_GRADED_API + restaurantID, { headers: this.headers });
  }

  gradeRestaurant(data: GradeModel): Observable<any> {
    return this.http.post(environment.SERVER_APP + this.GRADE_RESTAURANT_API, data, { headers: this.headers });
  }

  findAllByPage(page: number): Observable<PageObject<RestaurantModel>> {
    return this.http.get<PageObject<RestaurantModel>>(environment.SERVER_APP + this.RESTAURANT_PAGE_API + page, { headers: this.headers });
  }

  createRestaurant(data: RestaurantModel): Observable<any> {
    return this.http.post(environment.SERVER_APP + this.RESTAURANT_API, data,{ headers: this.headers });
  }

  filterRestaurants(data: FilterRestaurantsModel, page: number): Observable<PageObject<RestaurantModel>> {
    return this.http.post<PageObject<RestaurantModel>>(environment.SERVER_APP + this.RESTAURANT_FILTER_API + page, data, { headers: this.headers });
  }
}
