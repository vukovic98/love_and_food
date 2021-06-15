import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { UserPage } from 'src/app/models/user-page.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {

  displayedColumns: string[] = ['email', 'name', 'gender', 'dateOfBirth', 'rating'];

  dataSource: UserPage = { content: [], totalElements: 0, totalPages: 0, size: 0 };
  pageEvent: PageEvent = new PageEvent();

  filterForm = new FormGroup({
    name: new FormControl('', ),
  });

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllUsers()
  }

  getAllUsers() {
    this.userService
      .findAllUsers(0)
      .subscribe(
        res => {
          this.dataSource = res
        }

      )
  }

  onPaginateChange(event: PageEvent): void {
    this.pageEvent = event;
    this.getNewPage(this.pageEvent.pageIndex, this.pageEvent.pageSize);
  }

  getNewPage(index: number, size: number): void {
    this.userService
      .findAllUsers(index)
      .subscribe(
        res => {
          this.dataSource = res
        }
      );
  }

  filterUsers() {

  }

}
