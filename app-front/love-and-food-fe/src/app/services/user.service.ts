import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { UserDTO } from "../dto/user.dto";
import { AuthService } from "./auth.service";

@Injectable({
    providedIn: 'root'
  })
  export class UserService {
    
    private readonly ENDPOINT_REGISTER: string = "auth/register"

    private readonly ENDPOINT_VIEW_USERS :string = 'user'

    private headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authService.getToken()
    });

    constructor(private http: HttpClient, private authService: AuthService) {

    }

    register(userDTO: UserDTO) {
        return this.http.post<UserDTO>(environment.SERVER_APP + this.ENDPOINT_REGISTER,userDTO)
    }

    findAllUsers() {
      return this.http.get<UserDTO[]>(environment.SERVER_APP + this.ENDPOINT_VIEW_USERS, {headers: this.headers})
    }
  }