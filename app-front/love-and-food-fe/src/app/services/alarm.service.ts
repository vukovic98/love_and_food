import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageObject} from "../models/page.model";
import {AlarmModel} from "../models/alarm.model";
import {RestaurantModel} from "../models/restaurant.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AlarmService {

  private readonly ALARM_PAGE_API: string = "alarm/by-page/";

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(
    private http: HttpClient
  ) { }

  findAllByPage(page: number): Observable<PageObject<AlarmModel>> {
    return this.http.get<PageObject<AlarmModel>>(environment.SERVER_APP + this.ALARM_PAGE_API + page, { headers: this.headers });
  }
}
