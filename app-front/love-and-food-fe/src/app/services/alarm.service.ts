import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageObject} from "../models/page.model";
import {AlarmModel} from "../models/alarm.model";
import {RestaurantModel} from "../models/restaurant.model";
import {environment} from "../../environments/environment";
import { AlarmPage } from '../models/alarm-page.model';

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

  findAllByPage(page: number): Observable<AlarmPage> {
    return this.http.get<AlarmPage>(environment.SERVER_APP + this.ALARM_PAGE_API + page, { headers: this.headers });
  }
}
