import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {StationService} from '../service/station.service';
import {Station} from '../model/station.model';
import {Router} from '@angular/router';
import {UserService} from '../../_services/user.service';
import {OwnerService} from '../service/owner.service';
import {Owner} from '../model/owner.model';

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.css'],
  providers: [StationService, OwnerService, UserService]
})
export class StationComponent implements OnInit {

  constructor(private stationService: StationService
    , private userService: UserService
    , private ownerService: OwnerService
    , private router: Router) {
  }

  private user: Owner;
  public station: Station;
  public listStation: Array<Station> = [];

  ngOnInit() {
    const user = this.userService.getSavedUser();
    for (let i = 1; i < 5; ++i) {
      this.stationService.getStation(i).subscribe(
        data => this.listStation.push(data)
      );
    }
  }

  selectStation(station: Station) {
    this.station = station;
    console.log(station);
  }


}
