import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.css']
})
export class StationComponent implements OnInit {

  constructor() { }

  public barChartOptions:any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels:string[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  public barChartType:string = 'bar';
  public barChartLegend:boolean = true;
 
  public barChartData:any[] = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A'},
    {data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B'}
  ];
 
  public formGroup: FormGroup

  ngOnInit() {
    this.initFormGroup();
  }

  private initFormGroup() {
    this.formGroup = new FormGroup({
      name: new FormControl('', Validators.required)
      , address: new FormControl('', )
      , status: new FormControl('')
      , slots: new FormControl('')
      , service: new FormControl('')
      , vehicleType: new FormControl('')
      , ticketType: new FormControl('')
    })
  }

}
