import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Owner } from '../model/owner.model';
import { StationService } from '../service/station.service';
import { Station } from '../model/station.model';
import { OwnerService } from '../service/owner.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [StationService, OwnerService]
})
export class RegisterComponent implements OnInit {

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;

  owner: Owner;
  station: Station;
  location: string;
  constructor(private stationService: StationService
    , private ownerService: OwnerService
    , private snackBar: MatSnackBar
    , private router: Router) { }

  ngOnInit() {

    this.inputFirstForm();
    this.inputSecondForm();
  }

  private inputFirstForm() {
    this.firstFormGroup = new FormGroup({
      fullName: new FormControl('', Validators.required)
      , email: new FormControl('', [Validators.required, Validators.email])
      , phone: new FormControl('', [Validators.required])
      , address: new FormControl('', [Validators.required])
      , password: new FormControl('', [Validators.required])
    });

    this.thirdFormGroup = new FormGroup({
      fullName: new FormControl({ value: '', disabled: true }, Validators.required)
      , email: new FormControl({ value: '', disabled: true }, [Validators.required, Validators.email])
      , phone: new FormControl({ value: '', disabled: true }, [Validators.required])
      , address: new FormControl({ value: '', disabled: true }, [Validators.required])
      , password: new FormControl({ value: '', disabled: true }, [Validators.required])
    });

  }

  private inputSecondForm() {
    this.secondFormGroup = new FormGroup({
      stationName: new FormControl('', Validators.required)
      , openTime: new FormControl('', )
      , closedTime: new FormControl('')
      , slots: new FormControl('')
      , address: new FormControl('')
    });
  }


  // event
  public getLocation(e: any) {
    this.location = e.lat + ',' + e.lng;
    console.log('location: ' + this.location);
  }

  public onFirstStep() {
    this.owner = {
      phoneNumber:'+84 '+ this.firstFormGroup.controls['phone'].value
      , email: this.firstFormGroup.controls['email'].value
      , password: this.firstFormGroup.controls['password'].value
      , fullName: this.firstFormGroup.controls['fullName'].value
      , address: this.firstFormGroup.controls['address'].value
      , bankAccountNumber: 'DEFAULT'
      , status: 'Active'
      , bank: 'DEFAULT'
      , numStations: 1
    };

    this.thirdFormGroup = new FormGroup({
      fullName: new FormControl({ value: this.owner.fullName, disabled: true }, Validators.required)
      , email: new FormControl({ value: this.owner.email, disabled: true }, [Validators.required, Validators.email])
      , phone: new FormControl({ value: this.owner.phoneNumber, disabled: true }, [Validators.required])
      , address: new FormControl({ value: this.owner.address, disabled: true }, [Validators.required])
      , password: new FormControl({ value: this.owner.password, disabled: true }, [Validators.required])
    });
  }

  public onSecondStep() {
    this.station = {
      name: this.secondFormGroup.controls['stationName'].value
      , openTime: this.secondFormGroup.controls['openTime'].value
      , closeTime: this.secondFormGroup.controls['closedTime'].value
      , totalSlots: this.secondFormGroup.controls['slots'].value
      , address: this.secondFormGroup.controls['address'].value
      , status: 'Active'
      , createdDate: 1000
      , coordinate: this.location
      , services: []
      , usedSlots: 0
      , imageLink: ''
      , stationVehicleTypes: []
      , parkingMapLink: ''
      , holdingSlots: 0
      , star: 0
      , applicationID: ''
      , ownerID: ''
    };
  }

  public save() {
    console.log(this.owner);
    console.log(this.station);
    this.ownerService.addOwner(this.owner).subscribe(data => {
      console.log('id: ' + data.id);

      if (data.id) {
        this.station.ownerID = data.id;
        this.stationService.addStation(this.station).subscribe(it => {
          console.log('successful');
          this.snackBar.open('Them thanh cong', '', { duration: 2000 });
          this.router.navigate(['/']);
        });
      } else {

      }
    });
  }
}
