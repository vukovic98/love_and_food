import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CoupleDTO } from 'src/app/dto/couples.dto';
import { UserMVPDTO } from 'src/app/dto/user-mvp.dto';
import { UserDTO } from 'src/app/dto/user.dto';
import { LoveService } from 'src/app/services/love.service';

@Component({
  selector: 'app-user-reports',
  templateUrl: './user-reports.component.html',
  styleUrls: ['./user-reports.component.css']
})
export class UserReportsComponent implements OnInit {

  reportForm = new FormGroup({
    report: new FormControl('', Validators.required),
    matchedTimes: new FormControl({ value: 1, disabled: false }),
  });

  displayedColumnsLiars: string[] = ['email', 'name', 'gender', 'dateOfBirth'];
  displayedColumnsMvps: string[] = ['medal', 'rating', 'email', 'name', 'gender'];
  displayedColumnsCouples: string[] = ['user1', 'heart', 'user2'];

  showLiars: boolean = false
  liars: UserDTO[] = []

  showMVPs: boolean = false
  mvps: UserMVPDTO[] = []

  showCouples: boolean = false
  couples: CoupleDTO[] = []

  constructor(
    private loveService: LoveService
  ) { }

  ngOnInit(): void {
  }

  getReport(): void {
    //liars report
    if (this.reportForm.value.report === 'LIARS') {
      this.loveService.reportLiars()
        .subscribe(
          res => {
            this.liars = res
          }
        )
      this.showLiars = true
      this.showMVPs = false
      this.showCouples = false
    }

    //mvps report
    if (this.reportForm.value.report === 'MVPS') {
      this.loveService.reportMVPs()
        .subscribe(
          res => {
            this.mvps = res
          }
        )
      this.showMVPs = true
      this.showLiars = false
      this.showCouples = false
    }

    //couples report
    if (this.reportForm.value.report === 'COUPLES') {
      this.loveService.reportCouples(this.reportForm.value.matchedTimes)
        .subscribe(
          res => {
            this.couples = res
          }
        )
      this.showCouples = true
      this.showMVPs = false
      this.showLiars = false
    }
  }

  eventHandler(event){
    if(event.target.value.length == 1 && (event.code == "Backspace" || event.code == "Delete")){
      return false;
    }
 
    return true;
   }

}
