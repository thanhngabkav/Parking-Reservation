import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { VehicleType, Service } from '../model/ticket.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Station } from '../model/station.model';
import { StationService } from '../service/station.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
  providers: [StationService]
})
export class ManagerComponent implements OnInit {

  constructor(private activate: ActivatedRoute
    , private stationService: StationService
    , private router: Router) { }

  formGroup: FormGroup;

  types: CheckVehicleType[] = [];
  services: CheckService[] = [];
  status: string[] = []

  ngOnInit() {
    this.initFormGroup();
    this.initTypes()
    this.initServices()
    this.initStatus()
    this.activate.queryParams.subscribe(p => {
      let stationID = p['stationID'];
      this.stationService.getStation(stationID).subscribe(data => {
        this.addStationData(data)
      })
    })
  }

  initServices(): any {
    this.types.push({ id: 1, name: 'Xe Đạp', check: false });
    this.types.push({ id: 2, name: 'Xe Máy', check: false });
    this.types.push({ id: 3, name: 'Ô Tô', check: false });
    this.types.push({ id: 4, name: 'Xe Khách', check: false });
    this.types.push({ id: 5, name: 'Xe Tải', check: false });
  }

  initTypes(): any {
    this.services.push({ id: 1, name: 'Đỗ Xe', check: false });
    this.services.push({ id: 2, name: 'Sửa Xe', check: false });
    this.services.push({ id: 3, name: 'Đổ Xăng', check: false });
    this.services.push({ id: 4, name: 'Rửa Xe', check: false });
  }

  initStatus(): any {
    this.status.push("Active");
    this.status.push("Closed");
  }

  private initFormGroup() {
    this.formGroup = new FormGroup({
      name: new FormControl("", Validators.required),
      slots: new FormControl(""),
      status: new FormControl(""),
      address: new FormControl(""),
    })
  }

  private addStationData(station: Station) {
    this.formGroup = new FormGroup({
      name: new FormControl({ value: station.name, disabled: true }, Validators.required)
      , address: new FormControl({ value: station.address, disabled: true })
      , status: new FormControl({ value: station.status, disabled: true })
      , slots: new FormControl({ value: station.totalSlots, disabled: true })
    })

    station.services.forEach(x => {
      this.services.forEach(y => {
        if (x.serviceName == y.name)
          y.check = true;
      })
    })

    station.stationVehicleTypes.forEach(x => {
      this.types.forEach(y => {
        if (x.vehicleTypeId == y.id)
          y.check = true;
      })
    })

  }

  public location(e: any) {
    console.log(e);
  }

  public toTicketType(){
    this.router.navigate['/owner/ticket-type?station=1'];
  }

}

export interface CheckVehicleType {
  id: number;
  name: string;
  check: boolean;
}

export interface CheckService {
  id: number;
  name: string;
  check: boolean;
}