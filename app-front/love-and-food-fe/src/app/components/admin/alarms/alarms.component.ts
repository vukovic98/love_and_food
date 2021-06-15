import { Component, OnInit, ViewChild } from '@angular/core';
import {AlarmService} from "../../../services/alarm.service";
import {AlarmModel} from "../../../models/alarm.model";
import { AlarmPage } from 'src/app/models/alarm-page.model';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { FormGroup, FormControl } from '@angular/forms';
import { FilterAlarmDTO } from 'src/app/dto/filter-alarm.dto';

@Component({
  selector: 'app-alarms',
  templateUrl: './alarms.component.html',
  styleUrls: ['./alarms.component.css']
})
export class AlarmsComponent implements OnInit {

  filterForm = new FormGroup({
    alarmType: new FormControl('', ),
  });

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
    const filterAlarmDTO: FilterAlarmDTO = {
      alarmType: this.filterForm.value.alarmType
    };
    this.filterAlarms(index);
  }

  filterAlarms(pageNumber: number) {
    const alarmType = this.filterForm.value.alarmType;
    if (alarmType === "") {
      this.alarmService
      .findAllByPage(pageNumber)
      .subscribe(
        res => {
          this.alarms = res
        }
      );
    } else {
      const filterAlarmDTO: FilterAlarmDTO = {
        alarmType: alarmType
      };
      this.alarmService
        .filterAllByPage(pageNumber, filterAlarmDTO)
        .subscribe(
          res => {
            this.alarms = res
          }
        );
    }
  }

}
