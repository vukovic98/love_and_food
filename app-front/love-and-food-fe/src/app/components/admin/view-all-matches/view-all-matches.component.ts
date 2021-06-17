import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatchPage } from 'src/app/models/match-page.model';
import { MatchService } from 'src/app/services/match.service';

@Component({
  selector: 'app-view-all-matches',
  templateUrl: './view-all-matches.component.html',
  styleUrls: ['./view-all-matches.component.css']
})
export class ViewAllMatchesComponent implements OnInit {

  displayedColumns: string[] = ['couple', 'id', 'initiatorEmail', 'soulmateEmail', 'matchDate'];

  dataSource: MatchPage = { content: [], totalElements: 0, totalPages: 0, size: 0 };
  pageEvent: PageEvent = new PageEvent();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private matchService: MatchService) { }

  ngOnInit(): void {
    this.getAllUsers()
  }

  getAllUsers() {
    this.matchService
      .findAllMatches(0)
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
    this.matchService
      .findAllMatches(index)
      .subscribe(
        res => {
          this.dataSource = res
        }
      );
  }
}
