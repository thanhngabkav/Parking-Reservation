import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';
import {OwnerModule} from '../owner.module';
import {Owner} from '../model/owner.model';
import {Station} from '../model/station.model';
import {HttpHeaders} from '@angular/common/http';
import {TicketType} from '../model/ticket.model';

@Injectable()
export class TicketService {

  private apiUrl = 'http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/api';

  private finalHeader;

  constructor(private http: Http) {
    let token = localStorage.key['token'];
    let header = new Headers();
    header.append('Authorization', 'Bearer' + token);
    header.append('Content-Type', 'application/json');
    this.finalHeader = {headers: header};
  }

  getTicketType(stationID: number, serviceID: number): Observable<Array<TicketType>> {
    return this.http.get(this.apiUrl + '/tickettypes/find?' + 'serviceID=' + serviceID + '&stationID=' + stationID, this.finalHeader)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getTicketTypeByType(stationID: number, serviceID: number, typeID: string): Observable<TicketType> {
    return this.http.get(this.apiUrl + '/tickettypes/find?' + 'serviceID=' + serviceID + '&stationID=' + stationID + '&vehicleTypeID=' + typeID, this.finalHeader)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }


  postTicketType(types: TicketType): Observable<TicketType> {
    return this.http.post(this.apiUrl + '/tickettypes', types, this.finalHeader)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

}
