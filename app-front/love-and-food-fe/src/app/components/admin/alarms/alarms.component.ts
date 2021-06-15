import { Component, OnInit, ViewChild } from '@angular/core';
import {AlarmService} from "../../../services/alarm.service";
import {AlarmModel} from "../../../models/alarm.model";
import { AlarmPage } from 'src/app/models/alarm-page.model';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-alarms',
  templateUrl: './alarms.component.html',
  styleUrls: ['./alarms.component.css']
})
export class AlarmsComponent implements OnInit {

  public length = 0;
  public pageSize = 10;
  public pageIndex = 0;
  public showFirstLastButtons = true;

  displayedColumns: string[] = ['id', 'alarmType', 'message', 'date'];

  public alarms: AlarmPage = { content: [], totalElements: 0, totalPages: 0, size: 0 };

  pageEvent: PageEvent = new PageEvent();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private alarmService: AlarmService
  ) { }

  ngOnInit(): void {
    this.alarmService.findAllByPage(0).
    subscribe(res => {
      this.alarms = res;
    })
  }

  onPaginateChange(event: PageEvent): void {
    this.pageEvent = event;
    this.getNewPage(this.pageEvent.pageIndex, this.pageEvent.pageSize);
  }

  getNewPage(index: number, size: number): void {
    this.alarmService
      .findAllByPage(index)
      .subscribe(
        res => {
          this.alarms = res
        }
      );
  }

}
