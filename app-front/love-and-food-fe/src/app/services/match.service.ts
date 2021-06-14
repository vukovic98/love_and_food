import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import { MatchPage } from '../models/match-page.model';
import { MatchDTO } from '../dto/match.dto';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private readonly MATCH_API: string = "match"

  private readonly HAS_A_MATCH_API: string = "match/has-a-match"

  private readonly ENDPOINT_VIEW_MATCHES :string = 'match/by-page/'

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(
    private http: HttpClient,
  ) { }

  findById(matchId: number) {
    return this.http.get<MatchDTO>(environment.SERVER_APP + this.MATCH_API + '/' + matchId )
  }

  userHasAMatch(): Observable<any> {
    return this.http.get(environment.SERVER_APP + this.HAS_A_MATCH_API, { headers: this.headers });
  }

  findAllMatches(page: number) {
    return this.http.get<MatchPage>(environment.SERVER_APP + this.ENDPOINT_VIEW_MATCHES + page, {headers: this.headers})
  }

  findAllMatchesForUser() {
    return this.http.get<MatchDTO[]>(environment.SERVER_APP + this.MATCH_API, {headers: this.headers})
  }
}
