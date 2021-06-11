import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {DatePlaceModel} from "../models/date-place.model";
import {environment} from "../../environments/environment";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class DatePlaceService {

  private readonly DATE_PLACE_API: string = 'date-place/'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  getAllForUser(id: number): Observable<Array<DatePlaceModel>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    return this.http.get<Array<DatePlaceModel>>(environment.SERVER_APP + this.DATE_PLACE_API + id, { headers: headers });
  }
}
