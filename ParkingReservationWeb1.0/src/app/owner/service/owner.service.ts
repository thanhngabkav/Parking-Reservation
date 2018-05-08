import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Observable } from "rxjs/Observable";
import { OwnerModule } from '../owner.module';
import { Owner } from '../model/owner.model';
import { Station } from '../model/station.model';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class OwnerService {

  private apiUrl = 'http://localhost:8080/api/owners';

  private finalHeader;
  constructor(private http: Http) {
    let token = localStorage.key['token']
    let header = new Headers();
    header.append('Authorization', 'Bearer' + token)
    header.append('Content-Type', 'application/json')
    this.finalHeader = { headers: header }
  }

  addOwner(owner: Owner):Observable<Owner> {
    return this.http.post(this.apiUrl, owner, this.finalHeader)
    .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


  updateOwner(owner: Owner):Observable<Owner> {
    return this.http.put(this.apiUrl, owner, this.finalHeader)
    .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


  getOwnerById(id : String):Observable<Owner>{
    return this.http.get(this.apiUrl+'/' + id, this.finalHeader)
    .map((res: Response) => res.json())
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

}
