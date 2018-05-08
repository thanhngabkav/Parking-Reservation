import { Component, OnInit } from '@angular/core';
import { User } from '../_models/user';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [UserService, AuthenticationService]
})
export class HeaderComponent implements OnInit {
  public user: User;
  public isLogin = false;
  public isLoginSuccess = true;
  public loginModel: any = {};

  constructor(private router: Router, private userService: UserService, private auth: AuthenticationService) { }

  ngOnInit() {
    this.user = this.getLocalStorageUser();
    if (this.user != null)
      this.isLogin = true;
    else
      this.isLogin = false;
  }

  getLocalStorageUser(): User {
    let savedUser = JSON.parse(localStorage.getItem('user'));
    return savedUser;
  }


  goToManagerPage() {
    if (this.user.userType == 'ADMIN')
      this.router.navigate(['/member']);
      else
      this.router.navigate(['owner/station'])
  }

  login() {
    //call authentication service for login here
    console.log(this.loginModel);
    // this.auth.login(this.loginModel.userName,this.loginModel.password);
    this.auth.getToken(this.loginModel.userName, this.loginModel.password).subscribe(
      data => {
        this.auth.saveToken(data);
        let token = this.auth.getCredentials();
        console.log("access-token:" + token);

        if (token == null) {
          this.isLogin = false;
          this.isLoginSuccess = false;
        } else {
          this.isLogin = true;
          this.isLoginSuccess = true;
          this.userService.getUserByUserName(this.loginModel.userName).subscribe(
            user => {
              this.user = user;
              localStorage.setItem('user', JSON.stringify(this.user));
              this.router.navigate(["/"]);
            },
            err => {
              console.log(err);
            }
          );

        }
      },
      err => {
        console.log(err);
        this.isLogin = false;
        this.isLoginSuccess = false;
      }
    )

  }

  logout() {
    //call user service for logout here
    this.auth.logout();
    localStorage.removeItem('user');
    this.isLogin = false;
    this.router.navigate(["/"]);
  }

  accountManagerPage(){
    console.log("ID: "+this.user.id);
    this.router.navigate(['/owner/'+ this.user.id]);
  }

  register(){
    console.log("register: ");
    this.router.navigate(['/owner/register']);
  }
}
