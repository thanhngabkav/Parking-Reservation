import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from '../../_models/user';
import { OwnerService } from '../service/owner.service';
import { UserService } from '../../_services/user.service';
import { Owner } from '../model/owner.model';
import { MatSnackBar } from '@angular/material';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers: [UserService,OwnerService]

})
export class AccountComponent implements OnInit {

  public profileFormGroup: FormGroup;

  public updateSuccess = false;
  public updateFail = false;
  public user: User;
  public owner: Owner;
  OwnerStatus = [{id :1, value:'Active'},{id :2, value:'Waiting'},{id:3, value:'Suspended'}];

  constructor(private router: Router, private userService: UserService,
    private route: ActivatedRoute, private ownerService: OwnerService,private snackBar: MatSnackBar) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      let ownerId = params.id;
      console.log(ownerId)
      this.ownerService.getOwnerById(ownerId).subscribe(
        owner => {
          this.owner = owner;
          console.log(owner);
          this.user = JSON.parse(localStorage.getItem('user'));
          if(this.user.userType!= 'ADMIN' && this.user.id!= owner.id){
            this.router.navigate(['/Error']);
          }else{
            this.profileFormGroup = new FormGroup({
              status: new FormControl({ value: this.owner.status, disabled: this.user.userType != 'ADMIN' }, Validators.required)
              ,fullName: new FormControl({ value: this.owner.fullName, disabled: false }, Validators.required)
              , email: new FormControl({ value: this.owner.email, disabled: true }, [Validators.required])
              , phone: new FormControl({ value: this.owner.phoneNumber, disabled: true }, [Validators.required])
              , address: new FormControl({ value: this.owner.address, disabled: false }, [Validators.required])
              , password: new FormControl({ value: this.owner.password, disabled: false }, [Validators.required])
            }) 
          }
          
        },
        err => {
          console.log(err);
          this.router.navigate(['/error']);
        }
      )

    });
  }

  public onUpdateProfile() {
    this.updateFail = false;
    this.snackBar.open('Cập nhật thành công', '', { duration: 2000 });
    console.log("User:" + JSON.stringify(this.owner));
    console.log(this.owner.status);
    this.owner.address =  this.profileFormGroup.controls['address'].value;
    this.owner.fullName = this.profileFormGroup.controls['fullName'].value;
    this.owner.password = this.profileFormGroup.controls['password'].value;
    console.log("New User:" + JSON.stringify(this.owner));

    this.ownerService.updateOwner(this.owner).subscribe(
      data => {
        //console.log('id: ' + data.id);
        console.log("Data:" + JSON.stringify(data));
        this.updateSuccess = true;
        this.snackBar.open('Cập nhật thành công', '', { duration: 2000 });
      },
      err => {
        console.log(err);
        this.updateFail = true;
        this.snackBar.open('Cập nhật thất bại', '', { duration: 2000 });
      }
    );
    
  }

}
