import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {RestaurantModel} from "../models/restaurant.model";

@Injectable({
  providedIn: 'root'
})
export class FoodReportsService {

  private readonly BEST_GRADED_API: string = "restaurant/report/best-graded";
  private readonly RISING_API: string = "restaurant/report/rising-restaurant";
  private readonly DECLINING_API: string = "restaurant/report/declining-restaurant";
  private readonly MOST_VISITED_API: string = "restaurant/report/most-visited/";


  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  bestGradedReport(): Observable<Array<RestaurantModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.get<Array<RestaurantModel>>(environment.SERVER_APP + this.BEST_GRADED_API, { headers: headers });
  }

  risingReport(): Observable<Array<RestaurantModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.get<Array<RestaurantModel>>(environment.SERVER_APP + this.RISING_API, { headers: headers });
  }

  decliningReport(): Observable<Array<RestaurantModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.get<Array<RestaurantModel>>(environment.SERVER_APP + this.DECLINING_API, { headers: headers });
  }

  mostVisitedReport(season: string): Observable<Array<RestaurantModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.get<Array<RestaurantModel>>(environment.SERVER_APP + this.MOST_VISITED_API + season, { headers: headers });
  }
}
