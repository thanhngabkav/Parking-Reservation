import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, CookieXSRFStrategy } from '@angular/http';
import { User } from '../_models/user';
import { Observable } from "rxjs/Observable";
import { Cookie } from 'ng2-cookies/ng2-cookies';
import { Router } from '@angular/router';
import { UserService } from './user.service';
import { headersToString } from 'selenium-webdriver/http';

@Injectable()
export class AuthenticationService {

  private apiUrl = "http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/oauth/token?";
  private clientID = "WebApp";
  private secret = "WebApp_ParkingReservation";


  constructor(private http: Http, private router: Router) { }

  // /**
  //  * Login by user name and password, if success, save token in cookie
  //  * @param userName
  //  * @param password
  //  */
  // login(userName: string, password: string) {
  //   let params = new URLSearchParams();
  //   params.append('username', userName);
  //   params.append('password', password);
  //   params.append('grant_type', 'password');

  //   let header = new Headers({'Authorization': 'Basic '+btoa(this.clientID+':'+this.secret)});
  //   header.append('Content-Type', 'application/x-www-form-urlencoded');
  //   let options = new RequestOptions({ headers: header });

  //   //[Debug]
  //   // console.log("Basic:" + btoa(this.clientID + ':' + this.secret));
  //   // console.log("Params:" + params.toString());
  //   // console.log("header: " + JSON.stringify(options))

  //   let finalUrl = this.apiUrl + params.toString();

  //   this.http.post(finalUrl, null, {headers: header})
  //     .map(res => res.json())
  //     .subscribe(
  //       data => {
  //          this.saveToken(data);
  //       }
  //     )
  // }

  getToken(userName: string, password: string): Observable<any>{
    let params = new URLSearchParams();
    params.append('username', userName);
    params.append('password', password);
    params.append('grant_type', 'password');

    let header = new Headers({'Authorization': 'Basic '+btoa(this.clientID+':'+this.secret)});
    header.append('Content-Type', 'application/x-www-form-urlencoded');
    let options = new RequestOptions({ headers: header });

    //[Debug]
    // console.log("Basic:" + btoa(this.clientID + ':' + this.secret));
    // console.log("Params:" + params.toString());
    // console.log("header: " + JSON.stringify(options))

    let finalUrl = this.apiUrl + params.toString();
    return this.http.post(finalUrl, null, {headers: header})
      .map(res => res.json())
      .catch((error:any)=>Observable.throw(error.json().error || 'Server error'));

  }

  saveToken(token){
    console.log("data:" + token.access_token);
    var expireDate = new Date().getTime() + (token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    Cookie.set("access_token", token.access_token, expireDate);
    localStorage.setItem('access_token', token.access_token);
  }

  getCredentials(): String {
    return Cookie.get("access_token");
  }

  logout() {
    Cookie.delete('access_token');
  }

}
