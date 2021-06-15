import { Component, OnInit } from '@angular/core';
import {AlarmService} from "../../../services/alarm.service";
import {AlarmModel} from "../../../models/alarm.model";

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

  public alarms: Array<AlarmModel> = [];

  constructor(
    private alarmService: AlarmService
  ) { }

  ngOnInit(): void {
    this.alarmService.findAllByPage(0).subscribe((response) => {
      this.alarms = response.content;
      this.length = response.totalElements;
    })
  }

}
