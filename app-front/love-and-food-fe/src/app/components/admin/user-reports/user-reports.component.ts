import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  });

  displayedColumnsLiars: string[] = ['email', 'name', 'gender', 'dateOfBirth'];
  displayedColumnsMvps: string[] = ['medal', 'rating', 'email', 'name', 'gender'];

  showLiars: boolean = false
  liars: UserDTO[] = []

  showMVPs: boolean = false
  mvps: UserMVPDTO[] = []

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
            if(res.length !=0) {
              this.showLiars = true
            }
          }
        )
      this.showMVPs = false
    }

    //mvps report
    if (this.reportForm.value.report === 'MVPS') {
      this.loveService.reportMVPs()
        .subscribe(
          res => {
            this.mvps = res
            if(res.length !=0) {
              this.showMVPs = true
            }
          }
        )
      this.showLiars = false
    }
  }

}
