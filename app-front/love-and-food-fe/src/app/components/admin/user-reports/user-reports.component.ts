import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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

  displayedColumns: string[] = ['email', 'name', 'gender', 'dateOfBirth'];

  show: boolean = false

  liars: UserDTO[] = []

  constructor(
    private loveService: LoveService
  ) { }

  ngOnInit(): void {
  }

  getReport(): void {
    if (this.reportForm.value.report === 'LIARS') {
      this.loveService.reportLiars()
        .subscribe(
          res => {
            this.liars = res
          }
        )
    }

    this.show = true
  }

}
