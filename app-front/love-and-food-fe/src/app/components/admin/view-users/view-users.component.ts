import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/app/dto/user.dto';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {

  displayedColumns: string[] = ['email', 'name', 'gender', 'dateOfBirth'];

  dataSource: UserDTO[] = []

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllUsers()
  }

  getAllUsers() {
    this.userService
      .findAllUsers()
      .subscribe(
        res => {
          this.dataSource = res
        }
      )
  }

}
