import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SoulmateConfigDTO } from 'src/app/dto/soulmate-config.dto';
import { LoveService } from 'src/app/services/love.service';
import { SoulmateComponent } from '../soulmate/soulmate.component';

@Component({
  selector: 'app-user-preferences',
  templateUrl: './user-preferences.component.html',
  styleUrls: ['./user-preferences.component.css']
})
export class UserPreferencesComponent implements OnInit {

  configForm: FormGroup

  constructor(
    private loveService: LoveService,
    private _snackBar: MatSnackBar,
    ) 
    {
      this.configForm = new FormGroup({
        smokingPoints: new FormControl({ value:1, disabled: false }, [Validators.required, Validators.min(1), Validators.max(5)]),
        alcoholPoints: new FormControl({ value: 1, disabled: false }, [Validators.required, Validators.min(1), Validators.max(5)]),
        educationPoints: new FormControl({ value:1, disabled: false }, [Validators.required, Validators.min(1), Validators.max(5)]),
        traitPoints: new FormControl({ value:1, disabled: false }, [Validators.required, Validators.min(1), Validators.max(5)]),
        incomePoints: new FormControl({ value: 1, disabled: false }, [Validators.required, Validators.min(1), Validators.max(5)]),
        religionPoints: new FormControl({ value: 1, disabled: false }, [Validators.required, Validators.min(1), Validators.max(5)]),
      })
    }

  ngOnInit(): void {

    this.loveService.getConfiguration()
      .subscribe(
        res => {
          this.configForm.controls['smokingPoints'].setValue(res.smokingPoints)
          this.configForm.controls['alcoholPoints'].setValue(res.alcoholPoints)
          this.configForm.controls['traitPoints'].setValue(res.traitPoints)
          this.configForm.controls['educationPoints'].setValue(res.educationPoints)
          this.configForm.controls['incomePoints'].setValue(res.incomePoints)
          this.configForm.controls['religionPoints'].setValue(res.religionPoints)
        }
      )
  }

  submitConfiguration() {
    const soulmateConfigDTO: SoulmateConfigDTO = {
      smokingPoints: this.configForm.value.smokingPoints,
      alcoholPoints: this.configForm.value.alcoholPoints,
      educationPoints: this.configForm.value.educationPoints,
      traitPoints: this.configForm.value.traitPoints,
      incomePoints: this.configForm.value.incomePoints,
      religionPoints: this.configForm.value.religionPoints,
    }

    this.loveService.updateConfiguration(soulmateConfigDTO)
      .subscribe( res => {
        this._snackBar.open("Partner preferences successfully changed!", "Close", {
          duration: 2000,
          panelClass: ['purple-snackbar']
        })
      },
      error => {
        this._snackBar.open("* Something went wrong!", "Close", {
          duration: 2000,
          panelClass: ['purple-snackbar']
        })
      })
  }

  eventHandler(event){
    if(event.target.value.length == 1 && (event.code == "Backspace" || event.code == "Delete")){
      return false;
    }
 
    return true;
   }

}
