import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {RestaurantEntryModel} from "../../../../models/restaurant-entry.model";
import {getMultipleValuesInSingleSelectionError} from "@angular/cdk/collections";
import {MatDialog} from "@angular/material/dialog";
import {ChosenRestaurantComponent} from "../../choosen-restaurant/chosen-restaurant.component";

@Component({
  selector: 'app-find-restaurant-form',
  templateUrl: './find-restaurant-form.component.html',
  styleUrls: ['./find-restaurant-form.component.css']
})
export class FindRestaurantFormComponent implements OnInit {

  entryForm = new FormGroup({
    music: new FormControl('', Validators.required),
    cuisine: new FormControl('', Validators.required),
    onFoot: new FormControl('', Validators.required),
    hasGarden: new FormControl('', Validators.required),
    dateTime: new FormControl('', Validators.required),
  });

  constructor(
    private _snackBar: MatSnackBar,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  sendEntry() {
    if(this.entryForm.invalid) {
      this._snackBar.open("* Please fill out all fields!", "Close", {
        duration: 2000,
        panelClass: ['orange-snackbar']
      });
    } else {
      const dto: RestaurantEntryModel = {
        music: this.entryForm.value.music,
        cuisine: this.entryForm.value.cuisine,
        onFoot: this.entryForm.value.onFoot,
        hasGarden: this.entryForm.value.hasGarden,
        dateTime: this.entryForm.value.dateTime
      }

      this.openDialog(dto);

    }
  }

  private openDialog(dto: RestaurantEntryModel) {
    const dialogRef = this.dialog.open(ChosenRestaurantComponent, {
      width: '600px',
      height: '85%',
      data: dto,
      disableClose: true,
      panelClass: 'custom-dialog-container'
    });
  }
}
