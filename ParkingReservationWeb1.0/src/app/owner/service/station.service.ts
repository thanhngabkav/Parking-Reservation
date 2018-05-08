import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Observable } from "rxjs/Observable";
import { OwnerModule } from '../owner.module';
import { Owner } from '../model/owner.model';
import { Station, Report } from '../model/station.model';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class StationService {

  private apiUrl = 'http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/stations';

  private finalHeader;
  constructor(private http: Http) {

    let token = localStorage.key['token']
    let header = new Headers();
    header.append('Authorization', 'Bearer' + token)
    header.append('Content-Type', 'application/json')
    this.finalHeader = { headers: header }
  }

  addStation(station: Station): Observable<Station> {
    return this.http.post(this.apiUrl, station, this.finalHeader)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getStation(id: number): Observable<Station> {
    return this.http.get(this.apiUrl + '/' + id, this.finalHeader)
      .map((res: Response) => res.json())

  }

  getReport(stationId: number, year: string): Observable<Array<Report>> {
    return this.http.get(this.apiUrl + '/' + stationId + '/report?year=' + year)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable
        .throw(error.json().error || 'Server error'));
  }


}
