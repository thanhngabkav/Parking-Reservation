import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  displayedColumns = ['stt', 'type', 'price', 'service', 'description', 'xxxx'];
  dataSource = new MatTableDataSource<xxx>(ELEMENT_DATA);
  constructor() { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator
  }

}

export interface xxx {
  stt: number;
  type: string;
  service: string;
  price: number;
  description: string;
}

const ELEMENT_DATA: xxx[] = [
  { stt: 1, type: 'Hydrogen', price: 1.0079, service: 'H', description: 'nothing' },
  { stt: 2, type: 'Helium', price: 4.0026, service: 'He', description: 'nothing' },
  { stt: 3, type: 'Lithium', price: 6.941, service: 'Li', description: 'nothing' },
  { stt: 4, type: 'Beryllium', price: 9.0122, service: 'Be', description: 'nothing' },
  { stt: 5, type: 'Boron', price: 10.811, service: 'B', description: 'nothing' },
  { stt: 6, type: 'Carbon', price: 12.0107, service: 'C', description: 'nothing' },
  { stt: 7, type: 'Nitrogen', price: 14.0067, service: 'N', description: 'nothing' },
  { stt: 8, type: 'Oxygen', price: 15.9994, service: 'O', description: 'nothing' },
  { stt: 9, type: 'Fluorine', price: 18.9984, service: 'F', description: 'nothing' },
  { stt: 10, type: 'Neon', price: 20.1797, service: 'Ne', description: 'nothing' },
  { stt: 11, type: 'Sodium', price: 22.9897, service: 'Na', description: 'nothing' },
  { stt: 12, type: 'Magnesium', price: 24.305, service: 'Mg', description: 'nothing' },
  { stt: 13, type: 'Aluminum', price: 26.9815, service: 'Al', description: 'nothing' },
  { stt: 14, type: 'Silicon', price: 28.0855, service: 'Si', description: 'nothing' },
  { stt: 15, type: 'Phosphorus', price: 30.9738, service: 'P', description: 'nothing' },
  { stt: 16, type: 'Sulfur', price: 32.065, service: 'S', description: 'nothing' },
  { stt: 17, type: 'Chlorine', price: 35.453, service: 'Cl', description: 'nothing' },
  { stt: 18, type: 'Argon', price: 39.948, service: 'Ar', description: 'nothing' },
  { stt: 19, type: 'Potassium', price: 39.0983, service: 'K', description: 'nothing' },
  { stt: 20, type: 'Calcium', price: 40.078, service: 'Ca', description: 'nothing' },
];



