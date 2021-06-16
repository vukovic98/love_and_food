import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SoulmateDTO } from 'src/app/dto/soulmate.dto';
import { LoveService } from 'src/app/services/love.service';
import {ChosenRestaurantComponent} from "../../restaurant/choosen-restaurant/chosen-restaurant.component";
import {ContactSoulmateComponent} from "../../contact/contact-soulmate/contact-soulmate.component";

@Component({
  selector: 'app-soulmate',
  templateUrl: './soulmate.component.html',
  styleUrls: ['./soulmate.component.css']
})
export class SoulmateComponent implements OnInit {

  public found: boolean = false;

  public soulmate: SoulmateDTO;
  public pronoun: string;
  public pronounPos: string;
  public pronounKs: string;

  constructor(
    public dialogRef: MatDialogRef<SoulmateComponent>,
    private loveService: LoveService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loveService.findMatch()
    .subscribe(
      res => {
        const soulmateDTO: SoulmateDTO = {
          name: res.name,
          surname: res.surname,
          email: res.email,
          dateOfBirth: res.dateOfBirth,
          gender: res.gender,
          sexualOrientation: res.sexualOrientation,
          education: this.mapEducation(res.education),
          income: res.income,
          religion: res.religion,
          children: res.children,
          desiredRelationship: this.mapDesiredRelationship(res.desiredRelationship),
          location: res.location,
          alchocol: res.alchocol,
          smoking: res.smoking,
          personalityTraits: res.personalityTraits
        };
        this.soulmate = soulmateDTO
        this.pronounPos = soulmateDTO.gender === "MALE" ? "his" : "her";
        this.pronounKs = soulmateDTO.gender === "MALE" ? "him" : "her";
        this.pronoun = soulmateDTO.gender === "MALE" ? "he" : "she";
        this.found = true
      }
    )
  }

  findRestaurant() {
    this.dialogRef.close();
    this.router.navigate(['/restaurant/find-a-place'])
  }

  mapEducation(inputEducation: string) {
    switch (inputEducation) {
      case 'HIGH_SCHOOL':
        return 'finished high school';
      case 'BSC':
        return 'got a bachelors degree';
      case 'PHD':
        return 'got a doctors degree';
      default:
        break;
    }
  }

  mapDesiredRelationship(inputDesired: string) {
    let desired = inputDesired.toLowerCase().split('_').join(' ');
    if (desired.includes('term')) {
      return 'a ' + desired + ' relationship';
    }
  }

  getPersonalityFirstHalf() {
    let firstHalf = '';
    if (this.soulmate.personalityTraits.includes('E')) {
      firstHalf += 'extroverted, '
    } else if (this.soulmate.personalityTraits.includes('I')){
      firstHalf += 'introverted, '
    }
    if (this.soulmate.personalityTraits.includes('S')) {
      firstHalf += 'sensory'
    } else if (this.soulmate.personalityTraits.includes('N')){
      firstHalf += 'intuitive'
    }
    return firstHalf;
  }

  getPersonalitySecondHalf() {
    let secondHalf = '';
    if (this.soulmate.personalityTraits.includes('T')) {
      secondHalf += 'thinkers and '
    } else if (this.soulmate.personalityTraits.includes('F')){
      secondHalf += 'feelers and '
    }
    if (this.soulmate.personalityTraits.includes('J')) {
      secondHalf += 'judgers'
    } else if (this.soulmate.personalityTraits.includes('P')){
      secondHalf += 'perceivers'
    }
    return secondHalf;
  }

  openContactForm() {
    const dialogRef = this.dialog.open(ContactSoulmateComponent, {
      width: '70%',
      height: '81.95%',
      data: this.soulmate,
      panelClass: 'custom-dialog-container'
    });
  }
}
