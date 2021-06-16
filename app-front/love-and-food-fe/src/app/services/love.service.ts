import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { environment } from "src/environments/environment";
import { CoupleDTO } from "../dto/couples.dto";
import { MatchDTO } from "../dto/match.dto";
import { SoulmateConfigDTO } from "../dto/soulmate-config.dto";
import { UserMVPDTO } from "../dto/user-mvp.dto";
import { UserDTO } from "../dto/user.dto";

@Injectable({
providedIn: 'root'
})
export class LoveService {

  public matchInRating: BehaviorSubject<MatchDTO> = new BehaviorSubject<MatchDTO>(null);
  public ratingInProgress: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  private readonly ENDPOINT_LOVE: string = "love"
  private readonly ENDPOINT_RATE: string = "love/rate-date/"
  private readonly ENDPOINT_GET_CONFIG: string = "love/get-configuration"
  private readonly ENDPOINT_UPDATE_CONFIG: string = "love/update-configuration"
  private readonly ENDPOINT_LIARS: string = "love/report/liars"
  private readonly ENDPOINT_MVPS: string = "love/report/mvps"
  private readonly ENDPOINT_COUPLES: string = "love/report/couples/"

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

  getConfiguration() {
    return this.http.get<SoulmateConfigDTO>(environment.SERVER_APP + this.ENDPOINT_GET_CONFIG)
  }

  updateConfiguration(soulmateConfigDTO: SoulmateConfigDTO) {
    return this.http.put<void>(environment.SERVER_APP + this.ENDPOINT_UPDATE_CONFIG, soulmateConfigDTO)
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
}