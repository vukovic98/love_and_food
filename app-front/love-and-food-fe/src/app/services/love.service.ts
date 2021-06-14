import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { environment } from "src/environments/environment";
import { MatchDTO } from "../dto/match.dto";
import { UserDTO } from "../dto/user.dto";

@Injectable({
providedIn: 'root'
})
export class LoveService {

  public matchInRating: BehaviorSubject<MatchDTO> = new BehaviorSubject<MatchDTO>(null);
  public ratingInProgress: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  private readonly ENDPOINT_LOVE: string = "love"

  private readonly ENDPOINT_RATE: string = "love/rate-date/"

  private readonly ENDPOINT_LIARS: string = "love/report/liars"

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient) { }

  findMatch() {
    return this.http.get<UserDTO>(environment.SERVER_APP + this.ENDPOINT_LOVE + '/find-match', { headers: this.headers });
  }

  rateDate(matchId: number, rating: number) {
    return this.http.get<void>(environment.SERVER_APP + this.ENDPOINT_RATE + matchId + '/' + rating);
  }

  reportLiars() {
    return this.http.get<UserDTO[]>(environment.SERVER_APP + this.ENDPOINT_LIARS)
  }
}