import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { FilterUserDTO } from "../dto/filter-user.dto";
import { UserDTO } from "../dto/user.dto";
import { UserPage } from "../models/user-page.model";
import { AuthService } from "./auth.service";

@Injectable({
    providedIn: 'root'
  })
  export class UserService {
    
    private readonly ENDPOINT_REGISTER: string = "auth/register"

    private readonly ENDPOINT_VIEW_USERS: string = 'user/by-page/'

    private readonly ENDPOINT_FILTER_USERS: string = 'user/filter/by-page/'

    private headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    constructor(private http: HttpClient) {

    }

    register(userDTO: UserDTO) {
        return this.http.post<UserDTO>(environment.SERVER_APP + this.ENDPOINT_REGISTER,userDTO)
    }

    findAllUsers(page: number) {
      return this.http.get<UserPage>(environment.SERVER_APP + this.ENDPOINT_VIEW_USERS + page, {headers: this.headers})
    }

    filterUsers(page: number, filterUserDTO: FilterUserDTO) {
      return this.http.post<UserPage>(environment.SERVER_APP + this.ENDPOINT_FILTER_USERS + page, filterUserDTO, {headers: this.headers})
    }
  }