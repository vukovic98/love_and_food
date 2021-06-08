import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {loginResponse} from "../models/auth.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private readonly MATCH_API: string = "match/"
  private readonly HAS_A_MATCH_API: string = "match/has-a-match"

  constructor(private http: HttpClient) { }

  userHasAMatch(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.get(environment.SERVER_APP + this.HAS_A_MATCH_API, { headers: headers });
  }
}
