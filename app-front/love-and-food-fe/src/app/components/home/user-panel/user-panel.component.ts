import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SoulmateComponent } from '../soulmate/soulmate.component';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  findMatch() {
    this.openDialog()
  }

  private openDialog() {
    const dialogRef = this.dialog.open(SoulmateComponent, {
      width: '520px',
      height: '540px',
      disableClose: true,
    });
  }

}
