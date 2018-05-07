import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, CookieXSRFStrategy } from '@angular/http';
import { User } from '../_models/user';
import {Observable} from "rxjs/Observable";
import { Cookie } from 'ng2-cookies/ng2-cookies';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Injectable()
export class AuthenticationService {

  private apiUrl = "http://localhost:8080/oauth/token";
  private clientID ="WebApp";
  private secret = "WebApp_ParkingReservation";


  constructor(private http : Http, private router: Router) { }

  /**
   * Login by user name and password, if success, save token in cookie
   * @param userName 
   * @param password 
   */
  login(userName: string, password: string): String{
    let params = new URLSearchParams();
    params.append('username', userName);
    params.append('password', password);
    params.append('grant_type','password');
    let headers = new Headers({'Content_Type': 'application/x-www-form-urlencoded','Authorization': 'Basic '+btoa(this.clientID+':'+this.secret)});
    let options = new RequestOptions({ headers: headers });
    
    //[Debug]
    // console.log("Basic:" +btoa(this.clientID+':'+this.secret));
    console.log("Params:" + params.toString());
    this.http.post(this.apiUrl,params.toString(), options)
    .map(res => res.json())
    .subscribe(
      data => this.saveToken(data)
    )
  
    return this.getCredentials();
     
  }

  saveToken(token){
    console.log("data:" + token);
    var expireDate = new Date().getTime() + (token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
  }
  
  getCredentials(): String{
    return Cookie.get("access_token");
  } 
 
  logout() {
    Cookie.delete('access_token');
    this.router.navigate(['/']);
  }

}
