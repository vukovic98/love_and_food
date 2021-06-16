import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginModel, loginResponse, Role, TokenModel} from '../models/auth.model';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly ENDPOINT_LOGIN: string = "auth/log-in"

  constructor(private http: HttpClient, private route: Router) { }

  login(data: LoginModel): Observable<loginResponse> {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let message = JSON.stringify(data);

    return this.http.post<loginResponse>(environment.SERVER_APP + this.ENDPOINT_LOGIN, message, { headers: headers });
  }

  isAdmin(): boolean {
    let authorities = this.getUserAuthorities();
    let admin_role = "ROLE_ADMIN";

    for(let a of authorities) {
      if(admin_role === a.name)
        return true;
    }

    return false;
  }

  isUser(): boolean {
    let authorities = this.getUserAuthorities();
    let admin_role = "ROLE_USER";

    for(let a of authorities) {
      if(admin_role === a.name)
        return true;
    }

    return false;
  }

  isLoggedIn(): boolean {
    let token = this.getToken();
    return !!token;
  }

  decodeToken(token: string): TokenModel | null {
    if (token) {
      let payload = token.split(".")[1];
      payload = window.atob(payload);
      return JSON.parse(payload);
    } else return null;
  }

  getUserAuthorities(): Array<Role> {
    let token = this.getToken();
    if(token) {
      let model = this.decodeToken(token);
      return model?.authority ? model.authority : [];
    } else {
      return [];
    }
  }

  getUserID(): number {
    let token = this.getToken();
    if(token) {
      let model = this.decodeToken(token);
      return model?.user_id ? Number(model.user_id) : -1;
    } else {
      return -1;
    }
  }

  getUsersName(): string {
    let token = this.getToken();
    if(token) {
      let model = this.decodeToken(token);
      return model?.name ? model.name : '';
    } else {
      return '';
    }
  }

  getUsersEmail(): string {
    let token = this.getToken();
    if(token) {
      let model = this.decodeToken(token);
      return model?.sub ? model.sub : '';
    } else {
      return '';
    }
  }

  getToken(): string{
    return <string> localStorage.getItem("accessToken");
  }

  logout(): void {
    localStorage.removeItem("accessToken");
  }
}
