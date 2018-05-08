import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { StationService } from '../service/station.service';
import { Station } from '../model/station.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.css'],
  providers: [StationService]
})
export class StationComponent implements OnInit {

  constructor(private stationService: StationService
  , private router: Router) { }

  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels: string[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
  public barChartType: string = 'bar';
  public barChartLegend: boolean = true;

  public barChartData: any[] = [
    { data: [65, 59, 80, 81, 56, 55, 40, 80, 81, 56, 55, 40], label: 'Used Ticket' },
    { data: [28, 48, 40, 19, 86, 27, 90, 40, 19, 86, 27, 90], label: 'Used Ticket' }
  ];

  public years: number[] = [];
  public selectedYear: number;

  public formGroup: FormGroup

  ngOnInit() {
    localStorage['owner'] = '160f8bc0-b84a-45d2-9aa1-bcaa487f41cf';
    localStorage['token'] = 'V2ViQXBwOldlYkFwcF9QYXJraW5nUmVzZXJ2YXRpb24=';
    this.initFormGroup();
    this.initChart();
  }

  private initFormGroup() {
    this.formGroup = new FormGroup({
      name: new FormControl({value: '', disabled: true}, Validators.required)
      , address: new FormControl({value:'', disabled: true})
      , status: new FormControl({value: '', disabled: true})
      , slots: new FormControl({value: '', disabled: true})
      , service: new FormControl({value: '', disabled: true})
      , vehicleType: new FormControl({value: '', disabled: true})
      , ticketType: new FormControl({value:'', disabled: true})
    })
    this.stationService.getStation(1).subscribe(data => {
        this.addStationData(data)
    })

  }

  private addStationData(station: Station) {
    this.formGroup = new FormGroup({
      name: new FormControl({value: station.name, disabled: true}, Validators.required)
      , address: new FormControl({value: station.address, disabled: true})
      , status: new FormControl({value: station.status, disabled: true})
      , slots: new FormControl({value: station.totalSlots, disabled: true})
      , service: new FormControl({value: station.services.length, disabled: true})
      , vehicleType: new FormControl({value: station.stationVehicleTypes.length, disabled: true})
      , ticketType: new FormControl({value:'', disabled: true})
    })
  }

  private initChart() {
    this.selectedYear = (new Date()).getFullYear();
    for (let i = 2018; i <= this.selectedYear + 1; ++i) {
      this.years.push(i);
    }
  }

  // event
  public selectionChange(e: any) {
    let year = e.value;
    let id = 1;
    this.stationService.getReport(id, year).subscribe(
      values => {
        let used: number[] = [];
        let expired: number[] = [];
        values.forEach(e =>{
            used.push(e.numCheckedTickets)
            expired.push(e.numExpiredTickets)
        })
        this.barChartData = [
          { data: used, label: 'Used Ticket' },
          { data: expired, label: 'Expired Ticket' }
        ];
      }
    )
  }

  public edit(id: string){
    this.router.navigate(['/owner/manager'], {queryParams:{stationID: id}});
  }

}
