import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { TestAnswerDTO } from '../../../dto/test-answer.dto';
import { UserDTO } from '../../../dto/user.dto';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  personalInformation: FormGroup

  datingInformation1: FormGroup
  datingInformation2: FormGroup

  extrovertIntrovert: FormGroup
  sensorsIntuitive: FormGroup

  thinkerFeeler: FormGroup
  judgerPerceiver: FormGroup

  constructor(private userService: UserService, private route: Router) {
    //personal information form group
    this.personalInformation = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      dateOfBirth: new FormControl('', [Validators.required]),
      gender: new FormControl('', [Validators.required])
    })

    //dating information 1
    this.datingInformation1 = new FormGroup({
      sexualOrientation: new FormControl('', [Validators.required]),
      education: new FormControl('', [Validators.required]),
      income: new FormControl('', [Validators.required]),
      religion: new FormControl('', [Validators.required]),
      desiredRelationShipStatus: new FormControl('', [Validators.required]),
      children: new FormControl('', [Validators.required])
    })

    //dating information 2
    this.datingInformation2 = new FormGroup({
      location: new FormControl('', [Validators.required]),
      alcohol: new FormControl('', [Validators.required]),
      smoking: new FormControl('', [Validators.required])
    })

    //extroverts vs introverts
    this.extrovertIntrovert = new FormGroup({
      question1: new FormControl('', [Validators.required]),
      question2: new FormControl('', [Validators.required]),
      question3: new FormControl('', [Validators.required]),
      question4: new FormControl('', [Validators.required]),
      question5: new FormControl('', [Validators.required]),
    })

    //sensors vs intuitives
    this.sensorsIntuitive = new FormGroup({
      question6: new FormControl('', [Validators.required]),
      question7: new FormControl('', [Validators.required]),
      question8: new FormControl('', [Validators.required]),
      question9: new FormControl('', [Validators.required]),
      question10: new FormControl('', [Validators.required]),
    })

    //thinkers vs feelers
    this.thinkerFeeler = new FormGroup({
      question11: new FormControl('', [Validators.required]),
      question12: new FormControl('', [Validators.required]),
      question13: new FormControl('', [Validators.required]),
      question14: new FormControl('', [Validators.required]),
      question15: new FormControl('', [Validators.required]),
    })

    //judgers vs perceivers
    this.judgerPerceiver = new FormGroup({
      question16: new FormControl('', [Validators.required]),
      question17: new FormControl('', [Validators.required]),
      question18: new FormControl('', [Validators.required]),
      question19: new FormControl('', [Validators.required]),
      question20: new FormControl('', [Validators.required]),
    })
  }

  ngOnInit(): void {
  }

  getExtrovertIntrovert(testAnswers: TestAnswerDTO[]) {
    Object.keys(this.extrovertIntrovert.controls).forEach(key => {
      let testAnswer: TestAnswerDTO = {
        group: 'EI',
        answer: this.extrovertIntrovert.controls[key].value
      }
      testAnswers.push(testAnswer);
    });
  }

  getSensorsIntuitives(testAnswers: TestAnswerDTO[]) {
    Object.keys(this.sensorsIntuitive.controls).forEach(key => {
      let testAnswer: TestAnswerDTO = {
        group: 'SN',
        answer: this.sensorsIntuitive.controls[key].value
      }
      testAnswers.push(testAnswer);
    });
  }

  getThinkersFeelers(testAnswers: TestAnswerDTO[]) {
    Object.keys(this.thinkerFeeler.controls).forEach(key => {
      let testAnswer: TestAnswerDTO = {
        group: 'TF',
        answer: this.thinkerFeeler.controls[key].value
      }
      testAnswers.push(testAnswer);
    });
  }

  getJudgersPerceivers(testAnswers: TestAnswerDTO[]) {
    Object.keys(this.judgerPerceiver.controls).forEach(key => {
      let testAnswer: TestAnswerDTO = {
        group: 'JP',
        answer: this.judgerPerceiver.controls[key].value
      }
      testAnswers.push(testAnswer);
    });
  }

  onSubmit(): void {
    let testAnswers : TestAnswerDTO[] = []
    this.getExtrovertIntrovert(testAnswers)
    this.getSensorsIntuitives(testAnswers)
    this.getThinkersFeelers(testAnswers)
    this.getJudgersPerceivers(testAnswers)

    const userDTO: UserDTO = {
      name: this.personalInformation.value.firstName,
      surname: this.personalInformation.value.lastName,
      email: this.personalInformation.value.email,
      password: this.personalInformation.value.password,
      dateOfBirth: this.personalInformation.value.dateOfBirth,
      gender: this.personalInformation.value.gender,
      sexualOrientation: this.datingInformation1.value.sexualOrientation,
      education: this.datingInformation1.value.education,
      income: this.datingInformation1.value.income,
      religion: this.datingInformation1.value.religion,
      children: this.datingInformation1.value.children,
      desiredRelationship: this.datingInformation1.value.desiredRelationShipStatus,
      location: this.datingInformation2.value.location,
      alchocol: this.datingInformation2.value.alcohol,
      smoking: this.datingInformation2.value.smoking,
      testAnswers: testAnswers
    };

    this.userService.register(userDTO)
    .subscribe(res => {
      this.route.navigate(['/auth/login']);
    },
    error => {
      if (error.status === 403) {
        Swal.fire({
          icon: 'error',
          title: 'Email address taken',
          text: 'Account with given email address already exists.',
          confirmButtonColor: '#DC143C'
        });
      }
    })
  }

  getFieldErrorMessage(fieldName: string): string {
    if( fieldName === 'email' || fieldName === 'password' || fieldName === 'firstName' || fieldName === 'lastName' || fieldName ==='dateOfBirth' || fieldName === 'gender') {
      if (this.personalInformation.controls[fieldName].touched) {
        if(this.personalInformation.controls[fieldName].hasError('required')) {
          return '*This field is mandatory'
          } else if (this.personalInformation.controls[fieldName].hasError('email')) {
            return '*Email format is not valid'
        }
      }
    }
    else if (fieldName === 'sexualOrientation' || fieldName === 'education' || fieldName === 'income' || fieldName === 'religion' || fieldName ==='desiredRelationShipStatus' || fieldName === 'children') {
      if (this.datingInformation1.controls[fieldName].touched) {
        if(this.datingInformation1.controls[fieldName].hasError('required')) {
          return '*This field is mandatory'
        }
      }
    }
    else if(fieldName === 'location' || fieldName === 'alcohol' || fieldName === 'smoking') {
      if (this.datingInformation2.controls[fieldName].touched) {
        if(this.datingInformation2.controls[fieldName].hasError('required')) {
          return '*This field is mandatory'
        }
      }
    }
    else if(fieldName === 'question1' || fieldName === 'question2' || fieldName === 'question3'||fieldName === 'question4'||fieldName === 'question5') {
      if (this.extrovertIntrovert.controls[fieldName].touched) {
        if(this.extrovertIntrovert.controls[fieldName].hasError('required')) {
          return '*This field is mandatory'
        }
      }
    }
    else if(fieldName === 'question6' || fieldName === 'question7' || fieldName === 'question8'||fieldName === 'question9'||fieldName === 'question10') {
      if (this.sensorsIntuitive.controls[fieldName].touched) {
        if(this.sensorsIntuitive.controls[fieldName].hasError('required')) {
          return '*This field is mandatory'
        }
      }
    }
    else if(fieldName === 'question11' || fieldName === 'question12' || fieldName === 'question13'||fieldName === 'question14'||fieldName === 'question15') {
      if (this.thinkerFeeler.controls[fieldName].touched) {
        if(this.thinkerFeeler.controls[fieldName].hasError('required')) {
          return '*This field is mandatory'
        }
      }
    }
    else if(fieldName === 'question16' || fieldName === 'question17' || fieldName === 'question18'||fieldName === 'question19'||fieldName === 'question20') {
      if (this.judgerPerceiver.controls[fieldName].touched) {
        if(this.judgerPerceiver.controls[fieldName].hasError('required')) {
          return '*This field is mandatory'
        }
      }
    }
  }

}
