import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RestaurantEntryModel} from "../../../models/restaurant-entry.model";
import {SoulmateDTO} from "../../../dto/soulmate.dto";
import {ContactSoulmateDtoModel} from "../../../dto/contact-soulmate-dto.model";
import {LoveService} from "../../../services/love.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-contact-soulmate',
  templateUrl: './contact-soulmate.component.html',
  styleUrls: ['./contact-soulmate.component.css']
})
export class ContactSoulmateComponent implements OnInit {

  contactForm = new FormGroup({
    message: new FormControl('', Validators.required)
  });

  constructor(
    public dialogRef: MatDialogRef<ContactSoulmateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: SoulmateDTO,
    private loveService: LoveService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  fillMessage(param: string) {
    this.contactForm.value.message = param;
    this.contactForm.reset(this.contactForm.value)
  }

  contactSoulmate() {
    const dto: ContactSoulmateDtoModel = {
      soulmateEmail: this.data.email,
      message: this.contactForm.value.message
    };

    this.loveService.contactSoulmate(dto).subscribe((response) => {
      this._snackBar.open("You have successfully sent an email to your soulmate!", "Close", {
        duration: 2000,
        panelClass: ['purple-snackbar']
      });
    }, error => {
      this._snackBar.open("Sorry, something went wrong!", "Close", {
        duration: 2000,
        panelClass: ['purple-snackbar']
      });
    })
  }
}
