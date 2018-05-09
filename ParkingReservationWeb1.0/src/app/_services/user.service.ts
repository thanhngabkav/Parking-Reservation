import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {User} from '../_models/user';
import {Http, Headers, Response, RequestOptions} from '@angular/http';
import {Cookie} from 'ng2-cookies/ng2-cookies';

@Injectable()
export class UserService {

  private apiUrl = 'http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/api/users';
  private authorizationKey = 'Authorization';

  constructor(private http: Http) {
  }


  getUserByUserName(username: string): Observable<User> {
    let authorization = Cookie.get(this.authorizationKey);
    if (authorization === null) {
      null;
    }
    let headers = new Headers();
    // headers.append('Content-Type','application/json');
    headers.append(this.authorizationKey, authorization);
    const options = new RequestOptions({headers: headers});
    console.log(username);
    return this.http.get(this.apiUrl + '/find?userName=' + username, options)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getSavedUser(): User {
    return JSON.parse(localStorage.getItem('user'));
  }
}
