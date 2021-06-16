import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs";
import { environment } from "src/environments/environment";
import { CoupleDTO } from "../dto/couples.dto";
import { MatchDTO } from "../dto/match.dto";
import { UserMVPDTO } from "../dto/user-mvp.dto";
import { UserDTO } from "../dto/user.dto";
import {ContactSoulmateDtoModel} from "../dto/contact-soulmate-dto.model";

@Injectable({
providedIn: 'root'
})
export class LoveService {

  public matchInRating: BehaviorSubject<MatchDTO> = new BehaviorSubject<MatchDTO>(null);
  public ratingInProgress: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  private readonly ENDPOINT_LOVE: string = "love"
  private readonly ENDPOINT_RATE: string = "love/rate-date/"
  private readonly ENDPOINT_LIARS: string = "love/report/liars"
  private readonly ENDPOINT_MVPS: string = "love/report/mvps"
  private readonly ENDPOINT_COUPLES: string = "love/report/couples/"
  private readonly CONTACT_SOULMATE_API: string = "love/contact-soulmate";

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

  reportMVPs() {
    return this.http.get<UserMVPDTO[]>(environment.SERVER_APP + this.ENDPOINT_MVPS)
  }

  reportCouples(matchedTimes: number) {
    return this.http.get<CoupleDTO[]>(environment.SERVER_APP + this.ENDPOINT_COUPLES +  matchedTimes)
  }

  contactSoulmate(data: ContactSoulmateDtoModel): Observable<any> {
    console.log(data)
    return this.http.post(environment.SERVER_APP + this.CONTACT_SOULMATE_API, data, { headers: this.headers })
  }
}
