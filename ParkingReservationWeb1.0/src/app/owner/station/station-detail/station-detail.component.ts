import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Station} from '../../model/station.model';
import {StationService} from '../../service/station.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-station-detail',
  templateUrl: './station-detail.component.html',
  styleUrls: ['./station-detail.component.css'],
  providers: [StationService]
})
export class StationDetailComponent implements OnChanges {

  private formGroup: FormGroup;

  constructor(private stationService: StationService
  , private router: Router) {
  }

  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };

  public barChartLabels: string[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
  public barChartType = 'bar';
  public barChartLegend = true;

  public barChartData: any[] = [
    {data: [65, 59, 80, 81, 56, 55, 40, 80, 81, 56, 55, 40], label: 'Used Ticket'},
    {data: [28, 48, 40, 19, 86, 27, 90, 40, 19, 86, 27, 90], label: 'Expired Ticket'}
  ];

  @Input() station: Station;

  public years: number[] = [];
  public selectedYear: number;

  ngOnChanges(changes: SimpleChanges): void {
    this.initFormGroup();
    this.initChart();
  }


  private initFormGroup() {
    const station = this.station;

    this.formGroup = new FormGroup({
      name: new FormControl({value: station.name, disabled: true}, Validators.required)
      , address: new FormControl({value: station.address, disabled: true})
      , status: new FormControl({value: station.status, disabled: true})
      , slots: new FormControl({value: station.totalSlots, disabled: true})
      , service: new FormControl({value: station.services.length, disabled: true})
      , vehicleType: new FormControl({value: station.stationVehicleTypes.length, disabled: true})
      , ticketType: new FormControl({value: '', disabled: true})
    });

  }

  private initChart() {
    this.selectedYear = (new Date()).getFullYear();
    for (let i = 2018; i <= this.selectedYear + 1; ++i) {
      this.years.push(i);
    }
  }

  // event
  public selectionChange(e: any) {
    const year = e.value;
    const id = this.station.id;
    this.stationService.getReport(id, year).subscribe(
      values => {
        const used: number[] = [];
        const expired: number[] = [];
        values.forEach(e => {
          used.push(e.numCheckedTickets);
          expired.push(e.numExpiredTickets);
        });
        this.barChartData = [
          {data: used, label: 'Used Ticket'},
          {data: expired, label: 'Expired Ticket'}
        ];
      }
    );
  }

  public edit() {
    this.router.navigate(['/owner/manager'], {queryParams: {stationID: this.station.id}});
  }



}
