import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Observable } from "rxjs/Observable";
import { Owner } from './owner';

@Injectable()
export class OwnerService {


  private apiUrl = "http:localhost:8080/api/owners/";
  constructor(private http : Http){}

  findPageList(page : number): Observable<Owner[]>{
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    const options = new RequestOptions({headers: headers});
    return this.http.get(this.apiUrl+'?page='+page,options)
            .map((res:Response)=>res.json())
            .catch((error:any)=>Observable.throw(error.json().error || 'Server error'));
  }

  findAllOwners():Observable<Owner[]>{
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    const options = new RequestOptions({headers: headers});
    return this.http.get("http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/api/owners/all", options)
            .map((res:Response)=>res.json())
            .catch((error:any)=>Observable.throw(error.json().error || 'Server error'));

  }
}
