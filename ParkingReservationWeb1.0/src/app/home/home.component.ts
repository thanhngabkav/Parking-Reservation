import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { User } from '../_models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [UserService, AuthenticationService]
})
export class HomeComponent implements OnInit {

  public user: User;
  public isLogin = false;
  public isLoginSuccess = true;
  public loginModel : any = { };

  constructor(private router: Router, private userService: UserService, private auth: AuthenticationService) { }

  ngOnInit() {
      this.user = this.getLocalStorageUser();
      if(this.user!= null)
        this.isLogin = true;
      else
        this.isLogin = false;
  }

  getLocalStorageUser():User{
  //  let userTmp = new User();
  //  //get user from local here
  //  userTmp.id = "user ID"
  //  userTmp.email = "123";
  //  userTmp.phoneNumber = "Phone";
  //  userTmp.userType = "ADMIN";
  //  userTmp.status = "Active";
   
  //  if(userTmp!= null){
  //    return userTmp;
  //  }
   return null;
  }


  goToManagerPage(){
    if(this.user.userType == 'ADMIN')
      this.router.navigate(["/member"]);
  }

  login(){
    //call authentication service for login here
    console.log(this.loginModel);
    let token = this.auth.login(this.loginModel.userName,this.loginModel.password);
    if(token == null){
      this.isLogin = false;
      this.isLoginSuccess = false;
    }else{
      this.isLogin = true;
      this.isLoginSuccess = true;
      this.router.navigate(["/"]);
    }
  }

  logout(){
    //call user service for logout here

    this.isLogin = false;
    this.router.navigate(["/"]);
  }




}
