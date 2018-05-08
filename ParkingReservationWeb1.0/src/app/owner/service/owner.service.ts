import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';
import {Owner} from '../model/owner.model';


@Injectable()
export class OwnerService {

  private apiUrl = 'http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/api/owners';

  private finalHeader;

  constructor(private http: Http) {
    let token = localStorage.key['token'];
    let header = new Headers();
    header.append('Authorization', 'Bearer' + token);
    header.append('Content-Type', 'application/json');
    this.finalHeader = {headers: header};
  }

  addOwner(owner: Owner): Observable<Owner> {
    return this.http.post(this.apiUrl, owner, this.finalHeader)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


  updateOwner(owner: Owner): Observable<Owner> {
    return this.http.put(this.apiUrl, owner, this.finalHeader)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


  getOwnerById(id: String): Observable<Owner> {
    return this.http.get(this.apiUrl + '/' + id, this.finalHeader)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

}
