import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from '../../_models/user';
import { OwnerService } from '../service/owner.service';
import { UserService } from '../../_services/user.service';
import { Owner } from '../model/owner.model';

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
  private user: User;
  public owner: Owner;
  OwnerStatus = ['Active','Waiting','Suspended'];

  constructor(private router: Router, private userService: UserService,
    private route: ActivatedRoute, private ownerService: OwnerService) { }

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
            this.router.navigate(['/error']);
          }else{
            this.profileFormGroup = new FormGroup({
              fullName: new FormControl({ value: this.owner.fullName, disabled: false }, Validators.required)
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
    console.log("User:" + JSON.stringify(this.owner));
    this.profileFormGroup.controls['asdsad1'].value

    this.ownerService.updateOwner(this.owner).subscribe(
      data => {
        //console.log('id: ' + data.id);
        console.log("Data:" + JSON.stringify(data));
        this.updateSuccess = true;
      },
      err => {
        console.log(err);
        this.updateFail = true;
      }
    );
    
  }

}
