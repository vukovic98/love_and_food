import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { UserDTO } from "../dto/user.dto";
import { AuthService } from "./auth.service";

@Injectable({
providedIn: 'root'
})
export class LoveService {

  private readonly ENDPOINT_LOVE: string = "love"

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authService.getToken()
  });

  constructor(private http: HttpClient, private authService: AuthService) { }

  findMatch() {
    return this.http.get<UserDTO>(environment.SERVER_APP + this.ENDPOINT_LOVE + '/find-match', { headers: this.headers });
  }

}