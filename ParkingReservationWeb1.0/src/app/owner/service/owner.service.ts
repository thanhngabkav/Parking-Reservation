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

  private apiUrl = 'http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/api/owner';

  private finalHeader;
  constructor(private http: Http) {
    let token = localStorage.key['token']
    let header = new Headers();
    header.append('Authorization', 'Bearer' + token)
    header.append('Content-Type', 'application/json')
    this.finalHeader = { headers: header }
  }

  addOwner(owner: Owner) {
    return this.http.post(this.apiUrl, owner, this.finalHeader)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

}
