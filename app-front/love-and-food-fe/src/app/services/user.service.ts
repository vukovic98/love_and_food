import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { UserDTO } from "../components/dto/user.dto";

@Injectable({
    providedIn: 'root'
  })
  export class UserService {
    
    private readonly ENDPOINT_REGISTER: string = "auth/register"

    constructor(private http: HttpClient) {

    }

    register(userDTO: UserDTO) {
        return this.http.post<UserDTO>(environment.SERVER_APP + this.ENDPOINT_REGISTER,userDTO)
    }
  }