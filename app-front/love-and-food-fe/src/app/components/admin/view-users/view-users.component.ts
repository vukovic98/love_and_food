import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { FilterUserDTO } from 'src/app/dto/filter-user.dto';
import { UserPage } from 'src/app/models/user-page.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {

  displayedColumns: string[] = ['email', 'name', 'gender', 'personalityType', 'dateOfBirth', 'rating'];

  dataSource: UserPage = { content: [], totalElements: 0, totalPages: 0, size: 0 };
  pageEvent: PageEvent = new PageEvent();

  filterForm = new FormGroup({
    email: new FormControl('', ),
    name: new FormControl('', ),
    trait: new FormControl('', ),
    lowerRating: new FormControl('', ),
    upperRating: new FormControl('', ),
  });

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllUsers()
  }

  getAllUsers() {
    this.filterUsers();
  }

  onPaginateChange(event: PageEvent): void {
    this.pageEvent = event;
    this.getNewPage(this.pageEvent.pageIndex, this.pageEvent.pageSize);
  }

  getNewPage(index: number, size: number): void {
    const filterUserDTO = this.getFilterData();
    this.userService
      .filterUsers(index, filterUserDTO)
      .subscribe(
        res => {
          this.dataSource = res
        }
      );
  }

  filterUsers() {
    const filterUserDTO = this.getFilterData();
    this.userService
      .filterUsers(0, filterUserDTO)
      .subscribe(
        res => {
          this.dataSource = res
        }
      );
  }

  getFilterData(): FilterUserDTO {
    let lowerRating = this.filterForm.value.lowerRating;
    if (lowerRating === "" || lowerRating === null) {
      lowerRating = -1;
    }
    let upperRating = this.filterForm.value.upperRating;
    if (upperRating === "" || upperRating === null) {
      upperRating = -1;
    }

    const filterUserDTO: FilterUserDTO = {
      email: this.filterForm.value.email,
      name: this.filterForm.value.name,
      trait: this.filterForm.value.trait,
      lowerRating: lowerRating,
      upperRating: upperRating
    };

    return filterUserDTO;
  }
}
