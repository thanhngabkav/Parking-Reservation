import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  isLinear: boolean = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  constructor() { }

  ngOnInit() {
    // this.firstFormGroup = this._formBuilder.group({
    //   firstCtrl: ['', Validators.required]
    // });
    // this.secondFormGroup = this._formBuilder.group({
    //   secondCtrl: ['', Validators.required]
    // });
    this.inputFirstForm()
    this.inputSecondForm()
  }

  private inputFirstForm() {
    this.firstFormGroup = new FormGroup({
      fullName: new FormControl('', Validators.required)
      , email: new FormControl('', [Validators.required, Validators.email])
      , phone: new FormControl('', [Validators.required])
      , address: new FormControl('', [Validators.required])
      , password: new FormControl('', [Validators.required])
    })
  }

  private inputSecondForm() {
    this.secondFormGroup = new FormGroup({
      stationName: new FormControl('', Validators.required)
      , openTime: new FormControl('', )
      , closedTime: new FormControl('')
      , slots: new FormControl('')
    })
  }

}
