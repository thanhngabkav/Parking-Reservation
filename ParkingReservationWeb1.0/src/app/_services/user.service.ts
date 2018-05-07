import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { User } from '../_models/user';
import { Http, Headers, Response, RequestOptions } from "@angular/http";
import { Cookie } from 'ng2-cookies/ng2-cookies';

@Injectable()
export class UserService {

  private apiUrl = "http:localhost:8080/api/user";
  private authorizationKey = "Authorization";
  constructor(private http : Http) {}


  getUserById(id: string):Observable<User>{
      let authorization = Cookie.get(this.authorizationKey);
      if(authorization === null){
        null;
      }
      let headers = new Headers();
      headers.append('Content-Type','application/json');
      headers.append(this.authorizationKey,authorization);
      const options = new RequestOptions({headers: headers});
      return this.http.get(this.apiUrl+'/'+id, options)
      .map((res:Response)=>res.json())
      .catch((error:any)=>Observable.throw(error.json().error || 'Server error'));
  }
}
